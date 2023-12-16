package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Address;
import com.noenavintage.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AddressData extends JpaRepository<Address, Long> {
    Address findAddressByUser(User user);
}
