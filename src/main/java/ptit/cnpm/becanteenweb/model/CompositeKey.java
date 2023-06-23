package ptit.cnpm.becanteenweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKey implements Serializable {
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "PRODUCT_ID")
    private int productId;
}
