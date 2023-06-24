package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    private int roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;
    @OneToMany(mappedBy="role", fetch= FetchType.EAGER)
    @JsonIgnore
    private Collection<Account> accounts;
}
