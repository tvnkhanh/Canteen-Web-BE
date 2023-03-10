package ptit.cnpm.becanteenweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
    private String email;
    private String password;
    private int userId;
    private String role;
    private String status;
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
}
