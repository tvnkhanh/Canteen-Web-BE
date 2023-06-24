package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.Account;
import ptit.cnpm.becanteenweb.model.User;
import ptit.cnpm.becanteenweb.repository.AccountRepository;
import ptit.cnpm.becanteenweb.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/user-id/{email}")
    public int getUserIdByEmail(@PathVariable String email) {
        return userRepository.findByEmailAddress(email).getUserId();
    }
    @PostMapping("/user/{email}")
    public User newUser(@RequestBody User user, @PathVariable String email) {
        Account account = accountRepository.findByEmail(email);
        user.setAccount(account);
        return userRepository.save(user);
    }

    @GetMapping("/get-user/{id}")
    public User getUser(@PathVariable int id) {
        return userRepository.findById(id).get();
    }
}
