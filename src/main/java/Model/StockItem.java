package Model;
import Model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.math.BigDecimal;
@Entity
@Table(name="StockItem")
public class StockItem extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockID;
    private BigDecimal stockPrice;
    private String stockColour;
    private String stockSize;
    private int stockQty;

    // Default constructor is necessary for JPA entities
    public StockItem() {
    }

    // Stock Item Constructor
    public StockItem(long stockID, int productID, String productName, String productBrand, String productDescription, BigDecimal importPrice, BigDecimal productPrice, BigDecimal discountPrice, String supplierCode, String productImageURL, String thumbnailURL, BigDecimal stockPrice, String stockColour, String stockSize, int stockQty){
        super(productID, null, productName, productBrand, productDescription, importPrice, productPrice, discountPrice, supplierCode, productImageURL, thumbnailURL);
        this.stockID = stockID;
        this.stockPrice = stockPrice;
        this.stockColour = stockColour;
        this.stockSize = stockSize;
        this.stockQty = stockQty;
    }

    //Getters and setters
    public long getStockID(){return stockID;}
    public void setStockID(long stockID) {this.stockID = stockID;}
    public BigDecimal getStockPrice(){return stockPrice;}
    public void setStockPrice(BigDecimal stockPrice) {this.stockPrice = stockPrice;}
    public String getStockColour(){return stockColour;}
    public void setStockColour(String stockColour) {this.stockColour = stockColour;}
    public String getStockSize(){return stockSize;}
    public void setStockSize(String stockSize) {this.stockSize = stockSize;}
    public int getStockQty(){return stockQty;}
    public void setStockQty(int stockQty){this.stockQty = stockQty;}
}