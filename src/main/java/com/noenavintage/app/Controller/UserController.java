package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "https://192.168.8.9:8081")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserData userData;
    @Autowired
    public UserController(UserData userData) {
        this.userData = userData;
    }
    @GetMapping("/getall")
    public List<User> getAllUsers() {
        return userData.findAll();
    }
    @GetMapping("/get{userID}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userData.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to register a new user
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userData.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    // Search
    @GetMapping("/searchUser")
    public ResponseEntity<Optional<User>> searchUsers(
            @RequestParam(name = "userName", required = false) String userName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone) {
        if (userName != null) {
            Optional<User> user = userData.findByUserName(userName);
            return ResponseEntity.ok(user);
        } else if (email != null) {
            Optional<User> user = userData.findByEmail(email);
            return ResponseEntity.ok(user.isPresent() ? Optional.of(user.get()) : Optional.empty());
        } else if (phone != null) {
            Optional<User> user = userData.findByPhone(phone);
            return ResponseEntity.ok(user.isPresent() ? Optional.of(user.get()) : Optional.empty());
        } else {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
    }
    @PutMapping("/update{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userData.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            updatedUser.setUserID(id);
            User updated = userData.save(updatedUser);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userID) {
        Optional<User> existingUserOptional = userData.findByUserID(userID);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            userData.deleteByUserID(userID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}