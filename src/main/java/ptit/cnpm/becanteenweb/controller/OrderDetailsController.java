package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.CompositeKey;
import ptit.cnpm.becanteenweb.model.OrderDetails;
import ptit.cnpm.becanteenweb.model.Orders;
import ptit.cnpm.becanteenweb.model.Products;
import ptit.cnpm.becanteenweb.repository.OrderDetailsRepository;
import ptit.cnpm.becanteenweb.repository.OrdersRepository;
import ptit.cnpm.becanteenweb.repository.ProductsRepository;

@RestController
@CrossOrigin("*")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrdersRepository odRepo;

    @PostMapping("cart/{orderId}/{productId}/{quantity}")
    public HttpStatus updateQuantityCart(@PathVariable int orderId, @PathVariable int productId, @PathVariable int quantity) {
        Products p = productsRepository.findByProductId(productId);

        if (quantity <= p.getQuantity()) {
            ordersRepository.updateOrderDetails(orderId, productId, quantity);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;
    }

    @PostMapping("/cart/delete/{orderId}/{productId}")
    public void deleteCartItem(@PathVariable int orderId, @PathVariable int productId) {
        ordersRepository.deleteCartItem(orderId, productId);
    }

    @PostMapping("/addtocart/{orderId}/{productId}")
    public HttpStatus addToCart(@PathVariable int orderId, @PathVariable int productId) {
        Products p = productsRepository.findByProductId(productId);
        Orders o = odRepo.findByOrderId(orderId);
        OrderDetails od = ordersRepository.findCartItem(orderId, productId);

        if (od != null && p.getQuantity() > od.getQuantity()) {
            od.setQuantity(od.getQuantity() + 1);
            ordersRepository.save(od);
            return HttpStatus.OK;
        } else if (od == null && p.getQuantity() > 0) {
            OrderDetails newOd = new OrderDetails();
            CompositeKey compositeKey = new CompositeKey(orderId, productId);
            newOd.setId(compositeKey);
            newOd.setOrders(o);
            newOd.setProducts(p);
            newOd.setQuantity(1);
            ordersRepository.save(newOd);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;
    }
}
