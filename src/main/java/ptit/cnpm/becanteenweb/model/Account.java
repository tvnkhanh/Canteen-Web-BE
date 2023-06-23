package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String email;
    private String password;
    private String status;
    @ManyToOne
    @JoinColumn(name="roleId")
    @JsonBackReference
    private RoleEntity role;
    @OneToOne(mappedBy = "account")
    private User user;
}
