package ptit.cnpm.becanteenweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
