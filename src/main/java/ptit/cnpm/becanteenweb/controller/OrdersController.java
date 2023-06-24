package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.*;
import ptit.cnpm.becanteenweb.dto.OrdersDTO;
import ptit.cnpm.becanteenweb.repository.DeliveryRepository;
import ptit.cnpm.becanteenweb.repository.OrdersRepository;
import ptit.cnpm.becanteenweb.repository.PaymentRepository;
import ptit.cnpm.becanteenweb.repository.UserRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/orders-info")
    public List<OrdersDTO> getAllOrdersInfo() {
        List<OrdersDTO> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_ORDERS_INFO}");
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    OrdersDTO item = new OrdersDTO();
                    item.setUserId(rs.getInt("USER_ID"));
                    item.setOrderId(rs.getInt("ORDER_ID"));
                    item.setStatus(rs.getString("STATUS"));
                    item.setAddress(rs.getNString("ADDRESS"));
                    item.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
                    item.setOrderTime(rs.getTime("ORDER_TIME"));
                    item.setStartTime(rs.getTime("START_TIME"));
                    item.setArrival(rs.getTime("ARRIVAL"));
                    item.setDeliveryId(rs.getInt("DELIVERY_ID"));
                    item.setPaymentId(rs.getInt("PAYMENT_ID"));

                    result.add(item);
                }
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    @GetMapping("/orders-info/{status}/{id}")
    public OrdersDTO getOrderInfo(@PathVariable String status, @PathVariable int id) {
        OrdersDTO item = new OrdersDTO();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_ORDER(?, ?)}");
            stmt.setString(1, status);
            stmt.setInt(2, id);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    item.setUserId(rs.getInt("USER_ID"));
                    item.setOrderId(rs.getInt("ORDER_ID"));
                    item.setStatus(rs.getString("STATUS"));
                    item.setAddress(rs.getNString("ADDRESS"));
                    item.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
                    item.setOrderTime(rs.getTime("ORDER_TIME"));
                    item.setArrival(rs.getTime("ARRIVAL"));
                }
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return item;
    }

    @GetMapping("/get-my-orders/{id}")
    public List<OrdersDTO> getMyOrders(@PathVariable int id) {
        List<OrdersDTO> result = new ArrayList<>();

        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_MY_ORDERS(?)}");
            stmt.setInt(1, id);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    OrdersDTO item = new OrdersDTO();
                    item.setUserId(rs.getInt("USER_ID"));
                    item.setOrderId(rs.getInt("ORDER_ID"));
                    item.setStatus(rs.getString("STATUS"));
                    item.setPaymentId(rs.getInt("PAYMENT_ID"));
                    item.setDeliveryId(rs.getInt("DELIVERY_ID"));
                    item.setName(rs.getNString("NAME"));
                    item.setImage(rs.getString("IMAGE"));
                    item.setQuantity(rs.getInt("QUANTITY"));
                    item.setPrice(rs.getDouble("PRICE"));

                    result.add(item);
                }
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    @PostMapping("/new-order/{userId}")
    public Orders createOrder(@RequestBody Orders orders, @PathVariable int userId) {
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);
        delivery = deliveryRepository.getInitDelivery();

        Payment payment = paymentRepository.getPaymentByMethod(PaymentMethod.CASH);
        User user = userRepository.findByUserId(userId);

        orders.setDelivery(delivery);
        orders.setPayment(payment);
        orders.setUser(user);

        return ordersRepository.save(orders);
    }

    @PostMapping("/get-order/{orderId}")
    public HttpStatus handleGetOrder(@PathVariable int orderId) {
        Orders orderToGet = ordersRepository.findById(orderId).get();
        Collection<OrderDetails> orderDetailsList = orderToGet.getOrderDetails();

        for (OrderDetails orderDetails : orderDetailsList) {
            if (orderDetails.getProducts().getQuantity() < orderDetails.getQuantity()) {
                orderToGet.setStatus("CANCEL");
                ordersRepository.save(orderToGet);
                return HttpStatus.FORBIDDEN;
            }
        }

        for (OrderDetails orderDetails : orderDetailsList) {
            orderDetails.getProducts().setQuantity(orderDetails.getProducts().getQuantity() - orderDetails.getQuantity());
        }
        orderToGet.setStatus("RECEIVED");
        ordersRepository.save(orderToGet);
        return HttpStatus.OK;
    }

    @PostMapping("/done-order/{orderId}")
    public Orders handleDoneOrder(@PathVariable int orderId) {
        Orders orderToGet = ordersRepository.findById(orderId).get();
        orderToGet.setStatus("SUCCESS");

        return ordersRepository.save(orderToGet);
    }

    @PostMapping("/make-order/{orderId}")
    public HttpStatus handleMakeOrder(@PathVariable int orderId) {
        Orders orderToMake = ordersRepository.findById(orderId).get();
        Collection<OrderDetails> orderDetailsList = orderToMake.getOrderDetails();

        for (OrderDetails orderDetails : orderDetailsList) {
            if (orderDetails.getProducts().getQuantity() < orderDetails.getQuantity()) {
                return HttpStatus.FORBIDDEN;
            }
        }

        orderToMake.setStatus("ORDER");
        ordersRepository.save(orderToMake);
        return HttpStatus.OK;
    }

    @PostMapping("/cancel-order/{orderId}")
    public Orders handleCancelOrder(@PathVariable int orderId) {
        Orders orderToMake = ordersRepository.findById(orderId).get();
        orderToMake.setStatus("CANCEL");

        return ordersRepository.save(orderToMake);
    }
}
