package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PAYMENT")
public class Payment {
    @Id
    private int paymentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "METHOD")
    private PaymentMethod method;
    @OneToMany(mappedBy = "payment", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Orders> orders;
}
