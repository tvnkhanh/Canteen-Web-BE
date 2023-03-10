package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.OrderDetails;
import ptit.cnpm.becanteenweb.repository.OrderDetailsRepository;
import ptit.cnpm.becanteenweb.repository.ProductsRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;

    @PostMapping("cart/{orderId}/{productId}/{quantity}")
    public void updateQuantityCart(@PathVariable int orderId, @PathVariable int productId, @PathVariable int quantity) {
        ordersRepository.updateOrderDetails(orderId, productId, quantity);
    }

    @PostMapping("/cart/delete/{orderId}/{productId}")
    public void deleteCartItem(@PathVariable int orderId, @PathVariable int productId) {
        ordersRepository.deleteCartItem(orderId, productId);
    }

    @PostMapping("/addtocart/{orderId}/{productId}")
    public void addToCart(@PathVariable int orderId, @PathVariable int productId) {
        OrderDetails od = ordersRepository.findCartItem(orderId, productId);
        if (od != null) {
            ordersRepository.updateOrderDetails(orderId, productId, od.getQuantity() + 1);
        } else {
            OrderDetails newOd = new OrderDetails(orderId, productId, 1);
            ordersRepository.save(newOd);
        }

    }
}
