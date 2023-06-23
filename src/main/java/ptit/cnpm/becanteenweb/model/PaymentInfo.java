package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PAYMENT_INFO")
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pinfoId;
    private String cardHolder;
    private String creditCardNum;
    private Date validThru;
    private String cvv;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
}
