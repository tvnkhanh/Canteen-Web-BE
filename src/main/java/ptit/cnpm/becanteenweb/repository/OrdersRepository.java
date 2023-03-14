package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.cnpm.becanteenweb.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
