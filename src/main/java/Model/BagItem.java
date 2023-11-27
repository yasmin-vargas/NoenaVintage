package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import Model.Product;
import Model.StockItem;
import java.math.BigDecimal;
@Entity
@Table(name="BagItem")
public class BagItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bagItemID;
    private String productBrand;
    private String productName;
    private String bagItemColour; //This should be the stockItemColour the Customer chooses (not stockColour which are the colours in stock)
    private String bagItemSize;  //This should be the size the Customer chooses (not stockSize which are the sizes in stock)
    private int bagItemQty; //This should be the quantity the Customer chooses (not stockQuantity which is the quantity in stock)
    private BigDecimal bagItemPrice; // the price for the specific stockItem, which may include discount fx "Seamed Stockings, Beige Size Medium" on discount.
    @ManyToOne //Many to one relationship
    @JoinColumn(name = "stockItemId") // Specifies that the stockItemId column in the BagItem table is used as a foreign key to associate a BagItem with a StockItem.
    private long stockID;  // Reference to the StockItem
    private long userID;

    // Constructor to create a BagItem from StockItem
    public BagItem(long bagItemID, String productBrand, String productName, String bagItemColour, String bagItemSize, int bagItemQty, BigDecimal bagItemPrice, long stockID, long userID){
        this.bagItemID = bagItemID;
        this.productBrand = productBrand;
        this.productName = productName;
        this.bagItemColour = bagItemColour;
        this.bagItemSize = bagItemSize;
        this.bagItemQty = bagItemQty;
        this.bagItemPrice = bagItemPrice;
        this.stockID = stockID;
        this.userID = userID;
    }

    //Getters and setters
    public long getBagItemID(){return bagItemID;}
    public void setBagItemID(long stockID) {this.bagItemID = bagItemID;}
    public String getProductBrand(){return productBrand;}
    public void setProductBrand(String productBrand) {this.productBrand = productBrand;}
    public String getProductName(){return productName;}
    public void setProductName(String productName) {this.productName = productName;}
    public String getBagItemColour(){return bagItemColour;}
    public void setBagItemColour(String bagItemColour) {this.bagItemColour = bagItemColour;}
    public String getBagItemSize(){return bagItemSize;}
    public void setBagItemSize(String bagItemSize) {this.bagItemSize = bagItemSize;}
    public int getBagItemQty(){return bagItemQty;}
    public void setBagItemQty(int bagItemQty) {this.bagItemQty = bagItemQty;}
    public BigDecimal getBagItemPrice(){return bagItemPrice;}
    public void setBagItemPrice(BigDecimal bagItemPrice) {this.bagItemPrice = bagItemPrice;}
}
