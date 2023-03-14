package ptit.cnpm.becanteenweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompositeKeyAddress.class)
public class Address {
    @Id
    private String address;
    @Id
    private int userId;
}
