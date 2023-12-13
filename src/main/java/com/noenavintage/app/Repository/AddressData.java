package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressData extends JpaRepository<Address, Long> {
    List<Address> findByUserID(Long userID);
}
