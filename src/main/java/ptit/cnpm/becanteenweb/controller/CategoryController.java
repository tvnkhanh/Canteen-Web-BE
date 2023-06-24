package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.Category;
import ptit.cnpm.becanteenweb.model.Products;
import ptit.cnpm.becanteenweb.repository.CategoryRepository;
import ptit.cnpm.becanteenweb.repository.ProductsRepository;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @PostMapping("/category")
    public Category newCategory(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }
    @GetMapping("/get-categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/get-category/{categoryId}")
    public List<Products> getAllProductsByCategory(@PathVariable int categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        if (Objects.equals(category.getTitle(), "All")) {
            return productsRepository.findAll();
        }
        return (List<Products>) category.getProducts();
    }
}
