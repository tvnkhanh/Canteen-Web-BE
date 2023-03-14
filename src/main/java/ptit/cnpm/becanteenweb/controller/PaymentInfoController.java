package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.PaymentInfo;
import ptit.cnpm.becanteenweb.repository.PaymentInfoRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PaymentInfoController {
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @GetMapping("/get-cards/{userId}")
    public List<PaymentInfo> getCards(@PathVariable int userId) {
        return paymentInfoRepository.getCardById(userId);
    }

    @PostMapping("/card/save")
    public PaymentInfo saveCardInfo(@RequestBody PaymentInfo paymentInfo) {
        return paymentInfoRepository.save(paymentInfo);
    }

    @PostMapping("/card/update")
    public PaymentInfo updateCardInfo(@RequestBody PaymentInfo paymentInfo) {
        PaymentInfo paymentInfoToUpdate = paymentInfoRepository.findById(paymentInfo.getPinfoId()).get();
        paymentInfoToUpdate.setCardHolder(paymentInfo.getCardHolder());
        paymentInfoToUpdate.setCreditCardNum(paymentInfo.getCreditCardNum());
        paymentInfoToUpdate.setValidThru(paymentInfo.getValidThru());
        paymentInfoToUpdate.setCvv(paymentInfo.getCvv());

        return paymentInfoRepository.save(paymentInfo);
    }

    @PostMapping("/card/delete/{id}")
    public void deleteCardInfo(@PathVariable int id) {
        paymentInfoRepository.delete(paymentInfoRepository.findById(id).get());
    }
}
