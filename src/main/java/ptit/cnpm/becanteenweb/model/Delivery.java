package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DELIVERY")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;
    private String address;
    private Time orderTime;
    private Time startTime;
    private Time arrival;
    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Orders> orders;
}
