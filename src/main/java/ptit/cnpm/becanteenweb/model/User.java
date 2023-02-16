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
    private String name;
    private String gender;
    private String email;
    private String profileImg;
    private String status;
    private String role;
}
