package ptit.cnpm.becanteenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.model.User;
import ptit.cnpm.becanteenweb.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user-id/{email}")
    public int getUserIdByEmail(@PathVariable String email) {
        return userRepository.findByEmailAddress(email).getUserId();
    }
    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
