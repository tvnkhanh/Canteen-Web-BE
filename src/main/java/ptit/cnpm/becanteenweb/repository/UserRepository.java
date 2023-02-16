package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.cnpm.becanteenweb.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
