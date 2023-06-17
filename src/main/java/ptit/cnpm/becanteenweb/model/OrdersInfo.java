package ptit.cnpm.becanteenweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInfo {
    private int userId;
    private int orderId;
    private int paymentId;
    private int deliveryId;
    private String status;
    private String paymentMethod;
    private String address;
    private Time startTime;
    private Time orderTime;
    private Time arrival;
    private String name;
    private String image;
    private int quantity;
    private String description;
    private Double price;
}
