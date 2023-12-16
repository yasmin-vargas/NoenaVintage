package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserData extends JpaRepository<User, Long> {
    Optional<User> findByUserID(Long userID);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    void deleteByUserID(Long userID);
}
