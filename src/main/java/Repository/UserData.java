package Repository;
import Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserData extends JpaRepository<User, Long> {
    Optional<User> findByUserID(long userID);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> deleteByUserID(long userID);
}
