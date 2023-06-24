package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.cnpm.becanteenweb.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    @Query("select d from Delivery d where d.address = null and d.orderTime = null and d.startTime = null and d.arrival = null order by d.deliveryId DESC limit 1")
    Delivery getInitDelivery();
}
