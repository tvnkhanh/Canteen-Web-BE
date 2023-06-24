package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.Role;
import ptit.cnpm.becanteenweb.model.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(Role role);
}
