package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.cnpm.becanteenweb.model.Products;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Products findByProductId(int id);
    @Query("select p from Products p")
    List<Products> findAllProduct();
}
