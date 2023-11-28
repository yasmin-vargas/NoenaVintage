package Model;
import jakarta.persistence.*;
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;
    private String streetAddress;
    private int postalCode;
    private String city;
    private String country;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor
    public Address(long addressID, AddressTypeEnum addressType, String streetAddress, int postalCode, String city, String country) {
        this.addressID = addressID;
        this.addressType = addressType;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    // Getters and Setters
    public Long getAddressID() {return addressID;}
    public void setAddressID(Long addressID) {this.addressID = addressID;}
    public AddressTypeEnum getAddressType() {return addressType;}
    public void setAddressType(AddressTypeEnum addressType) {this.addressType = addressType;}
    public String getStreetAddress() {return streetAddress;}
    public void setStreetAddress(String streetAddress) {this.streetAddress = streetAddress;}
    public int getPostalCode() {return postalCode;}
    public void setPostalCode(int postalCode) {this.postalCode = postalCode;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
}
