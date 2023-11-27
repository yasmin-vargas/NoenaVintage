package Model;
import Model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
@Entity
@Table(name="StockItem")
public class StockItem extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockID;
    private double stockPrice;
    private String stockColour;
    private String stockSize;
    private int stockQty;

    // Stock Item Constructor
    public StockItem(long stockID, int productID, String productName, String productBrand, String productDecade, String productDescription, double importPrice, double productPrice, double discountPrice, List<String> productColour, List<String> productSize, int productQty, String supplierCode, double stockPrice, String stockColour, String stockSize, int stockQty){
        super(productID, null, productName, productBrand, productDecade, productDescription, importPrice, productPrice, discountPrice, productColour, productSize, productQty, supplierCode);
        this.stockID = stockID;
        this.stockPrice = stockPrice;
        this.stockColour = stockColour;
        this.stockSize = stockSize;
        this.stockQty = stockQty;
    }

    //Getters and setters
    public long getStockID(){return stockID;}
    public void setStockID(long stockID) {this.stockID = stockID;}
    public double getStockPrice(){return stockPrice;}
    public void setStockPrice(double stockPrice) {this.stockPrice = stockPrice;}
    public String getStockColour(){return stockColour;}
    public void setStockColour(String stockColour) {this.stockColour = stockColour;}
    public String getStockSize(){return stockSize;}
    public void setStockSize(String stockSize) {this.stockSize = stockSize;}
    public int getStockQty(){return stockQty;}
    public void setStockQty(int stockQty){this.stockQty = stockQty;}
}