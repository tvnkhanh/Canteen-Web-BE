package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.cnpm.becanteenweb.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
