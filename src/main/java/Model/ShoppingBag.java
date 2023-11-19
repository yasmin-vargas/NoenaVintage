package Model;
import Model.BagItem;
import Model.StockItem;
import java.util.ArrayList;
import java.util.List;
public class ShoppingBag { //Shopping Bag Constructor
    private List<BagItem> bagItems; // Declare a list of products/bagItems
    private static double ShippingCost = 7.95; // Define a constant shipping cost
    public ShoppingBag(){  //Initialize the "bagItems" list in the constructor
        bagItems = new ArrayList<>();
    }
    public List<BagItem> getBagItems() {
        return bagItems;
    }

    // Method to calculate getShippingCost() - just a static amount for simplicity
    public double getShippingCost() {
        return ShippingCost;
    }
    // Method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    public double getTotalAmount() {
        double totalAmount = 0.0; // Initialize variable "totalAmount" to 0.0
        for (StockItem bagItem : bagItems) {
            totalAmount += bagItem.getStockPrice()* bagItem.getBagItemQuantity();
        }
        return totalAmount;
    }
}