package ptit.cnpm.becanteenweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.account.email = ?1")
    User findByEmailAddress(String email);
    User findByUserId(int id);
}
