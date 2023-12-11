package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name="Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageID;
    private String imageURL;
    @ManyToMany(mappedBy = "images")
    private Set<Product> products;

    @ManyToMany(mappedBy = "images")
    private Set<Variant> variants;

    public Image() {  // Default constructor
    }
    public Image(String imageURL) {
        this.imageURL = imageURL;
    }

    // Getters and setters
    public long getImageID() {return imageID;}
    public void setImageID(long imageID) {this.imageID = imageID;}
    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
    public Set<Product> getProducts() {return products;}
    public void setProducts(Set<Product> products) {this.products = products;}
    public Set<Variant> getVariants() {return variants;}
    public void setVariants(Set<Variant> variants) {this.variants = variants;}
}


