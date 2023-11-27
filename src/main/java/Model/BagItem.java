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
@Entity
@Table(name="BagItem")
public class BagItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bagItemID;
    private long userID;
    private String productBrand;
    private String productName;
    private String bagItemColour; //This should be the stockItemColour the Customer chooses (not stockColour which are the colours in stock)
    private String bagItemSize;  //This should be the size the Customer chooses (not stockSize which are the sizes in stock)
    private int bagItemQty; //This should be the quantity the Customer chooses (not stockQuantity which is the quantity in stock)
    private double bagItemPrice; // the price for the specific stockItem, which may include discount fx "Seamed Stockings, Beige Size Medium" on discount.
    @ManyToOne //Many to one relationship
    @JoinColumn(name = "stockItemId") // Specifies that the stockItemId column in the BagItem table is used as a foreign key to associate a BagItem with a StockItem.
    private StockItem stockItem;  // Reference to the StockItem

    // Constructor to create a BagItem from StockItem
    public BagItem(long bagItemID, long userID, String productBrand, String productName, String bagItemColour, String bagItemSize, int bagItemQty, double bagItemPrice, StockItem stockItem){
        this.bagItemID = bagItemID;
        this.userID = userID;
        this.productBrand = productBrand;
        this.productName = productName;
        this.bagItemColour = bagItemColour;
        this.bagItemSize = bagItemSize;
        this.bagItemQty = bagItemQty;
        this.bagItemPrice = bagItemPrice;
        this.stockItem = stockItem;
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
    public double getBagItemPrice(){return bagItemPrice;}
    public void setBagItemPrice(double bagItemPrice) {this.bagItemPrice = bagItemPrice;}
}
