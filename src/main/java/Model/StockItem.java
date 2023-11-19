package Model;
@Entity
public class StockItem extends Product{
    private int stockID;
    private double stockPrice;
    private String stockColour;
    private String stockSize;
    private int stockQuantity;

    // Stock Item Constructor
    public StockItem(int stockID, double stockPrice, String stockColour, String stockSize, int stockQuantity){
        this.stockID = stockID;
        this.stockPrice = stockPrice;
        this.stockColour = stockColour;
        this.stockSize = stockSize;
        this.stockQuantity = stockQuantity;
    }

    //Getters and setters
    public int getStockID(){

        return stockID;
    }
    public void setStockID(int stockID) {

        this.stockID = stockID;
    }
    public double getStockPrice(){

        return stockPrice;
    }
    public void setStockPrice(double stockPrice) {

        this.stockPrice = stockPrice;
    }
    public String getStockColour(){

        return stockColour;
    }
    public void setStockColour(String stockColour) {

        this.stockColour = stockColour;
    }
    public String getStockSize(){

        return stockSize;
    }
    public void setStockSize(String stockSize) {

        this.stockSize = stockSize;
    }
    public int getStockQuantity(){

        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {

        this.stockQuantity = stockQuantity;
    }

}
