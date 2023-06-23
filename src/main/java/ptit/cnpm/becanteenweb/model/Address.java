package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address {
    @Id
    private String address;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;
}
