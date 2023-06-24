package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.PaymentInfo;
import ptit.cnpm.becanteenweb.model.User;
import ptit.cnpm.becanteenweb.repository.PaymentInfoRepository;
import ptit.cnpm.becanteenweb.repository.UserRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PaymentInfoController {
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/get-cards/{userId}")
    public List<PaymentInfo> getCards(@PathVariable int userId) {
        return paymentInfoRepository.getCardById(userId);
    }

    @PostMapping("/card/save/{userId}")
    public PaymentInfo saveCardInfo(@RequestBody PaymentInfo paymentInfo, @PathVariable int userId) {
        User user = userRepository.findByUserId(userId);
        paymentInfo.setUser(user);
        return paymentInfoRepository.save(paymentInfo);
    }

    @PostMapping("/card/update/{userId}")
    public PaymentInfo updateCardInfo(@RequestBody PaymentInfo paymentInfo, @PathVariable int userId) {
        PaymentInfo paymentInfoToUpdate = paymentInfoRepository.findPaymentInfoByPinfoId(paymentInfo.getPinfoId());
        User user = userRepository.findByUserId(userId);

        paymentInfoToUpdate.setCardHolder(paymentInfo.getCardHolder());
        paymentInfoToUpdate.setCreditCardNum(paymentInfo.getCreditCardNum());
        paymentInfoToUpdate.setValidThru(paymentInfo.getValidThru());
        paymentInfoToUpdate.setCvv(paymentInfo.getCvv());
        paymentInfoToUpdate.setUser(user);

        return paymentInfoRepository.save(paymentInfoToUpdate);
    }

    @PostMapping("/card/delete/{id}")
    public void deleteCardInfo(@PathVariable int id) {
        paymentInfoRepository.delete(paymentInfoRepository.findPaymentInfoByPinfoId(id));
    }
}
