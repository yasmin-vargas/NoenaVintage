package com.noenavintage.app.Model;
import jakarta.persistence.*;
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    private String streetAddress;
    private int zipCode;
    private String city;
    private String country;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public Address() {
    }

    public Address(String streetAddress, int zipCode, String city, String country) {
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    // Getters and Setters
    public Long getAddressID() {return addressID;}
    public void setAddressID(Long addressID) {this.addressID = addressID;}
    public String getStreetAddress() {return streetAddress;}
    public void setStreetAddress(String streetAddress) {this.streetAddress = streetAddress;}
    public int getZipCode() {return zipCode;}
    public void setZipCode(int zipCode) {this.zipCode = zipCode;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
}
