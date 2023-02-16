package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ptit.cnpm.becanteenweb.model.Category;
import ptit.cnpm.becanteenweb.repository.CategoryRepository;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping("/category")
    public Category newCategory(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }
}
