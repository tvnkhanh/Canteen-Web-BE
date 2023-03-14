package ptit.cnpm.becanteenweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}
