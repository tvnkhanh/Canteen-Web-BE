package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.dto.UserAddressDTO;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.*;
import ptit.cnpm.becanteenweb.repository.AddressRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/user/address/{userId}")
    public List<UserAddressDTO> getUserAddress(@PathVariable int userId) {
        List<UserAddressDTO> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_USER_ADDRESS(?)}");
            stmt.setInt(1, userId);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    UserAddressDTO item = new UserAddressDTO();

                    item.setFirstName(rs.getNString("FIRST_NAME"));
                    item.setLastName(rs.getNString("LAST_NAME"));
                    item.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                    item.setAddress(rs.getNString("ADDRESS"));

                    result.add(item);
                }
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    @PostMapping("/address/save")
    public Address saveAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @PostMapping("/address/delete")
    public void deleteAddress(@RequestBody Address address) {
        addressRepository.delete(address);
    }
}
