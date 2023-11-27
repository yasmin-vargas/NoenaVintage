package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
@Entity
@Table(name="Product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long productID;
    protected String categoryName;
    protected String productName;
    protected String productBrand;
    protected String productDecade;
    protected String productDescription;
    protected double importPrice;
    protected double productPrice;
    protected double discountPrice;
    protected List<String> productColour; //list of availabe colours
    protected List<String> productSize; //List of available sizes
    protected int stockQty;  //relation to stockQty (sum of all stockQty of one Product)
    protected String supplierCode;

    // No-argument constructor
    public Product() {
    }

    // Product Constructor
    public Product(long productID, String categoryName, String productName, String productBrand, String productDecade, String productDescription, double importPrice, double productPrice, double discountPrice,List<String> productColour,List<String> productSize, int stockQuantity, String supplierCode){
        this.productID = productID;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDecade = productDecade;
        this.productDescription = productDescription;
        this.importPrice = importPrice;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.productColour = productColour;
        this.productSize = productSize;
        this.stockQty = stockQty;
        this.supplierCode = supplierCode;
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
    public String getProductDecade(){
        return productDecade;
    }
    public void setProductDecade(String productDecade) {
        this.productDecade = productDecade;
    }
    public String getProductDescription(){
        return productDescription;
    }
    public void setProductDescription(String productDescription) {this.productDescription = productDescription;}
    public double getImportPrice(){
        return importPrice;
    }
    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }
    public double getProductPrice(){
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public double getDiscountPrice(){
        return discountPrice;
    }
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
    public List<String> getProductColour(){
        return productColour;
    }
    public void setProductColour(List<String> productColour) {
        this.productColour = productColour;
    }
    public List<String> getProductSize(){
        return productSize;
    }
    public void setProductSize(List<String> productSize) {this.productSize = productSize;}
    public int getStockQty(){
        return stockQty;
    }
    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
