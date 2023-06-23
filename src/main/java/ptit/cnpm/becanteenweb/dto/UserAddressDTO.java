package ptit.cnpm.becanteenweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}
