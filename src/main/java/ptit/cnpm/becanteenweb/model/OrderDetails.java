package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @EmbeddedId
    CompositeKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnore
    Orders orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    @JsonIgnore
    Products products;
    private int quantity;

}
