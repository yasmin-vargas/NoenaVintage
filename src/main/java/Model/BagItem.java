package Model;

public class BagItem extends StockItem {
    private int stockID;
    private String productBrand;
    private String productName;
    private double stockPrice; // the price for the specific stockItem, which may include discount fx "Seamed Stockings, Beige Size Medium" on discount.
    private String bagItemColour; //This should be the stockItemColour the Customer chooses (not stockColour which are the colours in stock)
    private String bagItemSize;  //This should be the size the Customer chooses (not stockSize which are the sizes in stock)
    private int bagItemQuantity; //This should be the quantity the Customer chooses (not stockQuantity which is the quantity in stock)

    // Stock Item Constructor
    public BagItem(int stockID, String productBrand, String productName, double stockPrice, String bagItemColour, String bagItemSize, int bagItemQuantity){
        this.stockID = stockID;
        this.productBrand = productBrand;
        this.productName = productName;
        this.stockPrice = stockPrice;
        this.bagItemColour = bagItemColour;
        this.bagItemSize = bagItemSize;
        this.bagItemQuantity = bagItemQuantity;
    }

    //Getters and setters
    public int getStockID(){

        return stockID;
    }
    public void setStockID(int stockID) {

        this.stockID = stockID;
    }

    public String getProductBrand(){

        return productBrand;
    }
    public void setProductBrand(String productBrand) {

        this.productBrand = productBrand;
    }
    public String getProductName(){

        return productName;
    }
    public void setProductName(String productName) {

        this.productName = productName;
    }

    public double getStockPrice(){

        return stockPrice;
    }
    public void setStockPrice(double stockPrice) {

        this.stockPrice = stockPrice;
    }
    public String getBagItemColour(){

        return bagItemColour;
    }
    public void setBagItemColour(String bagItemColour) {

        this.bagItemColour = bagItemColour;
    }
    public String getBagItemSize(){

        return bagItemSize;
    }
    public void setBagItemSize(String bagItemSize) {

        this.bagItemSize = bagItemSize;
    }
    public int getBagItemQuantity(){

        return bagItemQuantity;
    }
    public void setBagItemQuantity(int bagItemQuantity) {

        this.bagItemQuantity = bagItemQuantity;
    }
}
