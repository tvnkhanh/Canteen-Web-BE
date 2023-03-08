package ptit.cnpm.becanteenweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompositeKey.class)
public class OrderDetails {
    @Id
    private int orderId;
    @Id
    private int productId;
    private int quantity;

}
