package Controller;
import Model.User;
import Repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserData userData;
    @Autowired
    public UserController(UserData userData) {
        this.userData = userData;
    }
    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userData.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userData.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Search
    @GetMapping("/search")
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
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