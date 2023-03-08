package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.exception.AccountNotFoundException;
import ptit.cnpm.becanteenweb.model.Account;
import ptit.cnpm.becanteenweb.repository.AccountRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/user/{email}")
    public Account getAccount(@PathVariable String email) {
        return accountRepository.findById(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
    }

    @PostMapping("/account")
    public Account newAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }


}
