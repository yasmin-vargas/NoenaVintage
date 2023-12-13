package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;

@Entity
@Table(name="Variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantID;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    private String variantName;
    private String variantColour;
    private String variantSize;
    private BigDecimal variantPrice;
    private int variantQtyInStock;
    private String variantImageURL;   //Main image
    private String thumbnailURL;
    @OneToMany(mappedBy = "variant")
    private List<VariantImage> variantImages;
    public Variant() {  // Default constructor necessary for JPA entities
    }

    public Variant(Product product, String variantName, String variantColour, String variantSize, BigDecimal variantPrice, int variantQtyInStock, String variantImageURL, String thumbnailURL) {
        this.product = product;
        this.variantName = variantName;
        this.variantColour = variantColour;
        this.variantSize = variantSize;
        this.variantPrice = variantPrice;
        this.variantQtyInStock = variantQtyInStock;
        this.variantImageURL = variantImageURL;
        this.thumbnailURL = thumbnailURL;
    }

    // Getters and setters
    public long getVariantID() { return variantID; }
    public void setVariantID(long variantID) { this.variantID = variantID; }
    public String getVariantName() { return variantName; }
    public void setVariantName(String variantName) { this.variantName = variantName; }
    public BigDecimal getVariantPrice() { return variantPrice; }
    public void setVariantPrice(BigDecimal variantPrice) { this.variantPrice = variantPrice; }
    public String getVariantColour() { return variantColour; }
    public void setVariantColour(String variantColour) { this.variantColour = variantColour; }
    public String getVariantSize() { return variantSize; }
    public void setVariantSize(String variantSize) { this.variantSize = variantSize; }
    public int getVariantQtyInStock() { return variantQtyInStock; }
    public void setVariantQtyInStock(int variantQtyInStock) { this.variantQtyInStock = variantQtyInStock; }
    public String getVariantImageURL() { return variantImageURL; }
    public void setVariantImageURL(String variantImageURL) { this.variantImageURL = variantImageURL; }
    public String getThumbnailURL() { return thumbnailURL; }
    public void setThumbnailURL(String thumbnailURL) { this.thumbnailURL = thumbnailURL; }
}
