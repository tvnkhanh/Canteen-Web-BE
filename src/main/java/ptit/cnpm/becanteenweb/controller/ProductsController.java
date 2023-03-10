package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("product/add")
    public Products addProduct(@RequestBody Products product) {
        return productsRepository.save(product);
    }

    @PostMapping("product/edit/{id}")
    public Products editProduct(@PathVariable int id, @RequestBody Products pd) {
        Products productsToUpdate = productsRepository.getOne(id);
        productsToUpdate.setName(pd.getName());
        productsToUpdate.setPrice(pd.getPrice());
        productsToUpdate.setQuantity(pd.getQuantity());
        productsToUpdate.setImage(pd.getImage());
        productsToUpdate.setDescription(pd.getDescription());

        return productsRepository.save(productsToUpdate);
    }

    @PostMapping("product/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        Products productsToDelete = productsRepository.getOne(id);

        productsRepository.delete(productsToDelete);
    }
}
