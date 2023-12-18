package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Repository.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserData userData;
    @Autowired
    public UserController() {
    }
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userData.findAll();
    }
    @GetMapping("/get/{userID}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userID) {
        Optional<User> userOptional = userData.findByUserID(userID);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/update/{userID}")
    public ResponseEntity<User> updateUser(@PathVariable Long userID, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userData.findByUserID(userID);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            updatedUser.setUserID(userID);
            User updated = userData.save(updatedUser);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{userID}")
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