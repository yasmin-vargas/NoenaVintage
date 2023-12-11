package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import Model.Product;
import Repository.ProductData;
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
    private BigDecimal importPrice;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private int productQty;
    private String supplierCode;
    @OneToOne
    @JoinColumn(name = "main_image_id")  //Main image
    private Image productImageURL;

    @OneToMany(mappedBy = "product")
    private Set<Image> galleryImages = new HashSet<>();  //Gallery
    private Image thumbnailURL;
    private int bagItemQty;

    // No-argument constructor
    public Product() {
    }

    // Product Constructor
    public Product(long productID, String categoryName, String productName, String productBrand, String productDescription, String productColour, String productSize, BigDecimal importPrice, BigDecimal productPrice, BigDecimal discountPrice, int productQty, String supplierCode, Image productImageURL, Image thumbnailURL){
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
        this.productQty = productQty;
        this.supplierCode = supplierCode;
        this.productImageURL = productImageURL;
        this.thumbnailURL = thumbnailURL;
    }

    // Calculate total product quantity
    public int calculateStockQty(List<Product> products) {
        int totalQuantity = 0;
        for (Product product : products) {
            if (getProductID() == this.productID) {
                totalQuantity += getProductQty();
            }
        }
        return totalQuantity;
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
    public int getProductQty(){
        return productQty;
    }
    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public Image getProductImageURL(){return productImageURL;}
    public void setProductImageURL(Image productImageURL) {
        this.productImageURL = productImageURL;
    }
    public Image getThumbnailURL(){return thumbnailURL;}
    public void setThumbnailURL(Image thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
    public int getBagItemQty() {return bagItemQty;}
    public void setBagItemQty(int bagItemQty) {this.bagItemQty = bagItemQty;}
}


