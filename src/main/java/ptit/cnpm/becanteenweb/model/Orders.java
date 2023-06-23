package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String status;
    private Date date;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Collection<OrderDetails> orderDetails;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "paymentId")
    @JsonIgnore
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "deliveryId")
    @JsonIgnore
    private Delivery delivery;
}
