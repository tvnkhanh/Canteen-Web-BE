package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.cnpm.becanteenweb.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findByOrderId(int id);
}
