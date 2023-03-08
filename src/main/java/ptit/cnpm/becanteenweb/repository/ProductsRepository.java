package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.cnpm.becanteenweb.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
}
