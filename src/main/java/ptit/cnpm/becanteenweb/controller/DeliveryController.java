package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ptit.cnpm.becanteenweb.model.Delivery;
import ptit.cnpm.becanteenweb.repository.DeliveryRepository;

import java.sql.Time;

@RestController
@CrossOrigin("*")
public class DeliveryController {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @PostMapping("/set-time")
    public Delivery handleAddTime(@RequestBody Delivery delivery) {
        long milis = System.currentTimeMillis();
        Time time = new Time(milis);
        Delivery deliveryToChange = deliveryRepository.getOne(delivery.getDeliveryId());
        deliveryToChange.setStartTime(time);

        return deliveryRepository.save(deliveryToChange);
    }

    @PostMapping("/set-arrival-time")
    public Delivery handleAddArrivalTime(@RequestBody Delivery delivery) {
        long milis = System.currentTimeMillis();
        Time time = new Time(milis);
        Delivery deliveryToChange = deliveryRepository.getOne(delivery.getDeliveryId());
        deliveryToChange.setArrival(time);

        return deliveryRepository.save(deliveryToChange);
    }

    @PostMapping("/set-order-time")
    public Delivery handleAddOrderTime(@RequestBody Delivery delivery) {
        long milis = System.currentTimeMillis();
        Time time = new Time(milis);
        Delivery deliveryToChange = deliveryRepository.findById(delivery.getDeliveryId()).get();
        deliveryToChange.setOrderTime(time);
        deliveryToChange.setAddress(delivery.getAddress());

        return deliveryRepository.save(deliveryToChange);
    }
}
