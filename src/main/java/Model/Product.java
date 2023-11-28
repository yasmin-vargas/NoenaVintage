package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.math.BigDecimal;
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
    private BigDecimal importPrice;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private String supplierCode;
    private String productImageURL;
    private String thumbnailURL;

    // No-argument constructor
    public Product() {
    }

    // Product Constructor
    public Product(long productID, String categoryName, String productName, String productBrand, String productDescription, BigDecimal importPrice, BigDecimal productPrice, BigDecimal discountPrice, String supplierCode, String productImageURL, String thumbnailURL){
        this.productID = productID;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.importPrice = importPrice;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.supplierCode = supplierCode;
        this.productImageURL = productImageURL;
        this.thumbnailURL = thumbnailURL;
    }

    // Calculate total product quantity
    public int calculateStockQty(List<StockItem> stockItems) {
        int totalQuantity = 0;
        for (StockItem stockItem : stockItems) {
            if (stockItem.getProductID() == this.productID) {
                totalQuantity += stockItem.getStockQty();
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
    public String getSupplierCode(){
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public String getProductImageURL(){
        return productImageURL;
    }
    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }
    public String getThumbnailURL(){
        return thumbnailURL;
    }
    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
