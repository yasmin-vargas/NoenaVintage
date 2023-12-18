package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Repository.UserData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserData userData;
    @Autowired
    public LoginController() {
    }
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
    //Register a new user account
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = userData.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
