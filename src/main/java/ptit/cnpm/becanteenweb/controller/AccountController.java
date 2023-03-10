package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.*;
import ptit.cnpm.becanteenweb.repository.AccountRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

//    @GetMapping("/user/{email}")
//    public Account getAccount(@PathVariable String email) {
//        return accountRepository.findById(email)
//                .orElseThrow(() -> new AccountNotFoundException(email));
//    }

    @GetMapping("/user/{email}")
    public List<AccountInfo> getAccountInfo(@PathVariable String email) {
        List<AccountInfo> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_USER_INFO(?)}");
            stmt.setNString(1, email);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    AccountInfo item = new AccountInfo();
                    item.setEmail(rs.getNString("EMAIL"));
                    item.setPassword(rs.getString("PASSWORD"));
                    item.setUserId(rs.getInt("USER_ID"));
                    item.setRole(rs.getString("ROLE"));
                    item.setStatus(rs.getString("STATUS"));
                    item.setFirstName(rs.getNString("FIRST_NAME"));
                    item.setLastName(rs.getNString("LAST_NAME"));
                    item.setPhone(rs.getString("PHONE"));
                    item.setGender(rs.getString("GENDER"));

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

    @GetMapping("/users/{role}")
    public List<AccountInfo> getAllUserInfo(@PathVariable String role) {
        List<AccountInfo> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_ALL_USER_INFO(?)}");
            stmt.setNString(1, role);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    AccountInfo item = new AccountInfo();
                    item.setEmail(rs.getNString("EMAIL"));
                    item.setPassword(rs.getString("PASSWORD"));
                    item.setUserId(rs.getInt("USER_ID"));
                    item.setRole(rs.getString("ROLE"));
                    item.setStatus(rs.getString("STATUS"));
                    item.setFirstName(rs.getNString("FIRST_NAME"));
                    item.setLastName(rs.getNString("LAST_NAME"));
                    item.setPhone(rs.getString("PHONE"));
                    item.setGender(rs.getString("GENDER"));

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

    @PostMapping("/account/ban")
    public Account setBanAccount(@RequestBody Account account) {
        Account accountToBan = accountRepository.getOne(account.getEmail());
        accountToBan.setStatus("BANNED");

        return accountRepository.save(accountToBan);
    }

    @PostMapping("/account/active")
    public Account setUnBanAccount(@RequestBody Account account) {
        Account accountToUnBan = accountRepository.getOne(account.getEmail());
        accountToUnBan.setStatus("ACTIVE");

        return accountRepository.save(accountToUnBan);
    }

    @PostMapping("/account")
    public Account newAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }


}
