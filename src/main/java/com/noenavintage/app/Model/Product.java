package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.util.List;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="Product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
            joinColumns = @JoinColumn(name = "productID"),
            inverseJoinColumns = @JoinColumn(name = "categoryName")
    )
    private List<Category> productCategories;
    private String productName;
    private String productBrand;
    private String productDescription;
    private String productColour;
    private String productSize;
    private BigDecimal importPrice;
    private BigDecimal productPrice;
    private int qtyInStock;
    private String supplierCode;
    private String productImageURL;
    private String thumbnailURL;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Variant> variants;
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;
    @OneToMany(mappedBy = "product")
    private List<Attribute> productAttributes;

    public Product() {
    }

    public Product(List<Category> productCategories, String productName, String productBrand, String productDescription, String productColour, String productSize, BigDecimal importPrice, BigDecimal productPrice, int qtyInStock, String supplierCode, String productImageURL, String thumbnailURL, List<Variant> variants, List<ProductImage> productImages, List<Attribute> productAttributes){
        this.productCategories = productCategories;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productColour = productColour;
        this.productSize = productSize;
        this.importPrice = importPrice;
        this.productPrice = productPrice;
        this.qtyInStock = qtyInStock;
        this.supplierCode = supplierCode;
        this.productImageURL = productImageURL;
        this.thumbnailURL = thumbnailURL;
        this.variants = variants;
        this.productImages = productImages;
        this.productAttributes = productAttributes;
    }

    //Getters and setters
    public Long getProductID(){return productID;}
    public void setProductID(Long productID) {this.productID = productID;}
    public List<Category> getProductCategory() {return productCategories;}
    public void setProductCategory(List<Category> productCategories) {this.productCategories = productCategories;}
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
    public int getQtyInStock(){
        return qtyInStock;
    }
    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
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
    public void setThumbnailURL(String thumbnailURL) {this.thumbnailURL = thumbnailURL;}
    public List<Variant> getVariants() {return variants;}
    public void setVariants(List<Variant> variants) {this.variants = variants;}
    public List<ProductImage> getProductImages() {return productImages;}
    public void setProductImages(List<ProductImage> productImages) {this.productImages = productImages;}
    public List<Attribute> getAttributes() {return productAttributes;}
    public void setAttributes(List<Attribute> productAttributes) {this.productAttributes = productAttributes;}
}


