package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
