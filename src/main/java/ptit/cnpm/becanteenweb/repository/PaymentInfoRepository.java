package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.PaymentInfo;

import java.util.List;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {
    @Query("select p from PaymentInfo p where p.user.userId = ?1")
    List<PaymentInfo> getCardById(int userId);
    PaymentInfo findPaymentInfoByPinfoId(int id);
}
