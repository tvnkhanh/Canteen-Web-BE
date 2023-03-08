package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.cnpm.becanteenweb.model.Products;
import ptit.cnpm.becanteenweb.repository.ProductsRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;
    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
}
