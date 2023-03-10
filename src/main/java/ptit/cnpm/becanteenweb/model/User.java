package ptit.cnpm.becanteenweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[USER]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String profileImg;
}
