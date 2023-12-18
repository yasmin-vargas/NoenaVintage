package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Address;
import com.noenavintage.app.Repository.AddressData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressData addressData;

    @Autowired
    public AddressController() {
    }

    @GetMapping("/getAllAddresses")
    public List<Address> getAllAddresses() {
        return addressData.findAll();
    }

    @GetMapping("/get/{addressID}")
    public ResponseEntity<Address> getAddressByID(@PathVariable Long addressID) {
        Optional<Address> addressOptional = addressData.findByAddressID(addressID);
        return addressOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createAddress")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressData.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @PutMapping("/update/{addressID}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressID, @RequestBody Address updatedAddress) {
        Optional<Address> existingAddressOptional = addressData.findByAddressID(addressID);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            updatedAddress.setAddressID(addressID);
            Address updated = addressData.save(updatedAddress);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{addressID}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressID) {
        Optional<Address> existingAddressOptional = addressData.findByAddressID(addressID);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            addressData.deleteByAddressID(addressID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}