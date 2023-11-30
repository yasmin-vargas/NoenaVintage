package Controller;

import Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
@RestController
@RequestMapping("/api/login")
public class LoginController {
    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String userName = user.getUserName();
        String enteredPassword = user.getUserPassword();
        // Retrieve user by userName
        User storedUser = userData.findByUserName(userName).orElse(null);
        if (storedUser != null && storedUser.login(enteredPassword)) {
            return ResponseEntity.ok("Login successful");  // Authentication successful
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");  // Authentication failed
        }
    }
    //Register an account
    @PostMapping("/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userData.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
