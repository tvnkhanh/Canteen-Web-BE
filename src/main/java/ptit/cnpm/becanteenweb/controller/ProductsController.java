package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.AccountInfo;
import ptit.cnpm.becanteenweb.model.Products;
import ptit.cnpm.becanteenweb.repository.ProductsRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    @GetMapping("/product/{name}")
    public List<Products> getProduct(@PathVariable String name) {
        List<Products> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_SEARCH(?)}");
            stmt.setNString(1, name);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    Products item = new Products();
                    item.setProductId(rs.getInt("PRODUCT_ID"));
                    item.setName(rs.getNString("NAME"));
                    item.setPrice(rs.getDouble("PRICE"));
                    item.setQuantity(rs.getInt("QUANTITY"));
                    item.setImage(rs.getString("IMAGE"));
                    item.setDescription(rs.getNString("DESCRIPTION"));

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
