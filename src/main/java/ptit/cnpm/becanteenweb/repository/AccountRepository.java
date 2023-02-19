package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
