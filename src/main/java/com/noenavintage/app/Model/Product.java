package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="Product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;
    private String categoryName;
    private String productName;
    private String productBrand;
    private String productDescription;
    private String productColour;
    private String productSize;
    @Column(name = "importPrice", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal importPrice;
    @Column(name = "productPrice", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal productPrice;
    @Column(name = "discountPrice", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal discountPrice;
    private int QtyInStock;
    private String supplierCode;
    private String productImageURL;
    private String thumbnailURL;
    @ManyToMany
    @JoinTable(name = "ProductImage",
            joinColumns = @JoinColumn(name = "productID"),
            inverseJoinColumns = @JoinColumn(name = "imageID"))
    private Set<Image> images;
    public Product() {   // No-argument constructor
    }

    // Product Constructor
    public Product(long productID, String categoryName, String productName, String productBrand, String productDescription, String productColour, String productSize, BigDecimal importPrice, BigDecimal productPrice, BigDecimal discountPrice, int QtyInStock, String supplierCode, String productImageURL, String thumbnailURL){
        this.productID = productID;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productColour = productColour;
        this.productSize = productSize;
        this.importPrice = importPrice;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.QtyInStock = QtyInStock;
        this.supplierCode = supplierCode;
        this.productImageURL = productImageURL;
        this.thumbnailURL = thumbnailURL;
    }

    //Getters and setters
    public long getProductID(){return productID;}
    public void setProductID(long productID) {this.productID = productID;}
    public String getCategoryName(){return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}
    public String getProductName(){return productName;}
    public void setProductName(String productName) {this.productName = productName;}
    public String getProductBrand(){
        return productBrand;
    }
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
    public String getProductDescription(){
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public String getProductColour(){
        return productColour;
    }
    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }
    public String getProductSize(){
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public BigDecimal getImportPrice(){
        return importPrice;
    }
    public void setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
    }
    public BigDecimal getProductPrice(){
        return productPrice;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    public BigDecimal getDiscountPrice(){
        return discountPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    public int getQtyInStock(){
        return QtyInStock;
    }
    public void setQtyInStock(int QtyInStock) {
        this.QtyInStock = QtyInStock;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public String getProductImageURL(){return productImageURL;}
    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }
    public String getThumbnailURL(){return thumbnailURL;}
    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}


