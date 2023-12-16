package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Address;
import com.noenavintage.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AddressData extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByUser(User user);
    Optional<Address> findAddressByCity(String city);

    Optional<Address> findAddressByZipCode(int zipCode);

    Optional<Address> findAddressByCountry(String country);


    // find addresses and delete by ID
    Optional<Address> findByAddressID(Long addressID);
    void deleteByAddressID(Long addressID);

}
