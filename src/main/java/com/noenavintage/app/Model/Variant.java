package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import java.sql.Timestamp;

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
    private BigDecimal variantPrice;
    private String variantColour;
    private String variantSize;
    private int QtyInStock;
    private String variantImageURL;   //Main image
    private String thumbnailURL;
    @OneToMany(mappedBy = "variant")
    private List<VariantImage> variantImages;
    private Timestamp regDate;
    public Variant() {  // Default constructor necessary for JPA entities
    }

    public Variant(Product product, String variantName, BigDecimal variantPrice, String variantColour, String variantSize, int QtyInStock, String variantImageURL, String thumbnailURL, List<VariantImage> variantImages, Timestamp regDate) {
        this.product = product;
        this.variantName = variantName;
        this.variantPrice = variantPrice;
        this.variantColour = variantColour;
        this.variantSize = variantSize;
        this.QtyInStock = QtyInStock;
        this.variantImageURL = variantImageURL;
        this.thumbnailURL = thumbnailURL;
        this.variantImages = variantImages;
        this.regDate = regDate;
    }

    // Getters and setters
    public Long getVariantID() { return variantID; }
    public void setVariantID(Long variantID) { this.variantID = variantID; }
    public String getVariantName() { return variantName; }
    public void setVariantName(String variantName) { this.variantName = variantName; }
    public BigDecimal getVariantPrice() { return variantPrice; }
    public void setVariantPrice(BigDecimal variantPrice) { this.variantPrice = variantPrice; }
    public String getVariantColour() { return variantColour; }
    public void setVariantColour(String variantColour) { this.variantColour = variantColour; }
    public String getVariantSize() { return variantSize; }
    public void setVariantSize(String variantSize) { this.variantSize = variantSize; }
    public int getQtyInStock() { return QtyInStock; }
    public void setQtyInStock(int QtyInStock) { this.QtyInStock = QtyInStock; }
    public String getVariantImageURL() { return variantImageURL; }
    public void setVariantImageURL(String variantImageURL) { this.variantImageURL = variantImageURL; }
    public String getThumbnailURL() { return thumbnailURL; }
    public void setThumbnailURL(String thumbnailURL) { this.thumbnailURL = thumbnailURL; }
    public List<VariantImage> getVariantImages() {return variantImages;}
    public void setVariantImages(List<VariantImage> variantImages) {this.variantImages = variantImages;}
    public Timestamp getRegDate() {return regDate;}
    public void setRegDate(Timestamp regDate) {this.regDate = regDate;}
}
