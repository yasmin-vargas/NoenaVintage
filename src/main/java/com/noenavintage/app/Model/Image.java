package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID;
    private String imageURL;
    @OneToMany(mappedBy = "image")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "image")
    private List<VariantImage> variantImages;

    public Image() {  // Default constructor
    }
    public Image(String imageURL, List<ProductImage> productImages, List<VariantImage> variantImages) {
        this.imageURL = imageURL;
        this.productImages = productImages;
        this.variantImages = variantImages;
    }

    // Getters and setters
    public long getImageID() {return imageID;}
    public void setImageID(long imageID) {this.imageID = imageID;}
    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
    public List<ProductImage> getProductImages() {return productImages;}
    public void setProductImages(List<ProductImage> productImages) {this.productImages = productImages;}
    public List<VariantImage> getVariantImages() {return variantImages;}
    public void setVariantImages(List<VariantImage> variantImages) {this.variantImages = variantImages;}
}


