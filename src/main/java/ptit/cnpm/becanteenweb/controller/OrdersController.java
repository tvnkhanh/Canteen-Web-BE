package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.Delivery;
import ptit.cnpm.becanteenweb.model.Orders;
import ptit.cnpm.becanteenweb.model.OrdersInfo;
import ptit.cnpm.becanteenweb.repository.DeliveryRepository;
import ptit.cnpm.becanteenweb.repository.OrdersRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/orders-info")
    public List<OrdersInfo> getAllOrdersInfo() {
        List<OrdersInfo> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_ORDERS_INFO}");
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    OrdersInfo item = new OrdersInfo();
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
    public OrdersInfo getOrderInfo(@PathVariable String status, @PathVariable int id) {
        OrdersInfo item = new OrdersInfo();
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
    public List<OrdersInfo> getMyOrders(@PathVariable int id) {
        List<OrdersInfo> result = new ArrayList<>();

        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_MY_ORDERS(?)}");
            stmt.setInt(1, id);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    OrdersInfo item = new OrdersInfo();
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

    @PostMapping("/new-order")
    public Orders createOrder(@RequestBody Orders orders) {
        Delivery delivery = deliveryRepository.getInitDelivery();
        orders.setDeliveryId(delivery.getDeliveryId());

        return ordersRepository.save(orders);
    }

    @PostMapping("/get-order")
    public Orders handleGetOrder(@RequestBody Orders order) {
        Orders orderToGet = ordersRepository.findById(order.getOrderId()).get();
        orderToGet.setStatus("RECEIVED");

        return ordersRepository.save(orderToGet);
    }

    @PostMapping("/done-order")
    public Orders handleDoneOrder(@RequestBody Orders order) {
        Orders orderToGet = ordersRepository.findById(order.getOrderId()).get();
        orderToGet.setStatus("SUCCESS");

        return ordersRepository.save(orderToGet);
    }

    @PostMapping("/make-order")
    public Orders handleMakeOrder(@RequestBody Orders order) {
        Orders orderToMake = ordersRepository.findById(order.getOrderId()).get();
        orderToMake.setStatus("ORDER");

        return ordersRepository.save(orderToMake);
    }

    @PostMapping("/cancel-order")
    public Orders handleCancelOrder(@RequestBody Orders order) {
        Orders orderToMake = ordersRepository.findById(order.getOrderId()).get();
        orderToMake.setStatus("CANCEL");

        return ordersRepository.save(orderToMake);
    }
}
