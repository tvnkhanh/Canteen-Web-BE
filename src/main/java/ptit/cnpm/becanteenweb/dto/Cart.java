package ptit.cnpm.becanteenweb.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQuery(name = "Cart.cartInfo",
        procedureName = "CART_INFO", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = Integer.class)},
        resultClasses = {Cart.class})
public class Cart {
    @Id
    private int orderId;
    private int productId;
    private int quantity;
    private String status;
    private String name;
    private Double price;
    private int inStock;
    private String description;
    private String image;
    private int deliveryId;
    private int paymentId;
}
