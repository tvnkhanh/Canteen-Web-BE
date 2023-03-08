package ptit.cnpm.becanteenweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    @Modifying
    @Transactional
    @Query("update OrderDetails u set u.quantity = :quantity where u.orderId = :orderId and u.productId = :productId")
    void updateOrderDetails(int orderId, int productId, int quantity);

    @Modifying
    @Transactional
    @Query("delete from OrderDetails od where od.orderId = :orderId and od.productId = :productId")
    void deleteCartItem(int orderId, int productId);

    @Query("select od from OrderDetails od where od.orderId = ?1 and od.productId = ?2")
    OrderDetails findCartItem(int orderId, int productId);
}
