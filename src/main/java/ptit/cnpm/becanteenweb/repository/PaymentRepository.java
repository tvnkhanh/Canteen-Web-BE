package ptit.cnpm.becanteenweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.cnpm.becanteenweb.model.Payment;
import ptit.cnpm.becanteenweb.model.PaymentMethod;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment getPaymentByMethod(PaymentMethod paymentMethod);
}
