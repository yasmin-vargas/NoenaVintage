package Controller;
import Model.StockItem;
import Model.BagItem;
import Model.ShoppingBag;
import java.util.List;

public class ShoppingBagController {  //Shopping Bag Controller Constructor
    private ShoppingBag shoppingBag;  //"ShoppingBag" instance as constructor parameter
    public ShoppingBagController(ShoppingBag shoppingBag){
        this.shoppingBag = shoppingBag;
    }
    // Helper method to convert stockItems into bagItems
    private BagItem convertToBagItem (StockItem stockItem)  {
        //Creates a new bagItem using information from the StockItem
        BagItem bagItem = new BagItem();
        bagItem.setProductBrand(stockItem.getProductBrand()); //Getter & setter methods from StockItem model
        bagItem.setProductName(stockItem.getProductName());
        bagItem.setStockPrice(stockItem.getStockPrice());
        bagItem.setStockColour(stockItem.getStockColour());
        bagItem.setStockSize(stockItem.getStockSize());
        bagItem.setStockQuantity(1);  // Set the initial quantity to 1, since the bagItem is being added to the ShoppingBag
        return bagItem;
    }

    // Adds stockItems to ShoppingBag
    public void addToBag(StockItem stockItem) {  //data model "StockItem"
        BagItem bagItem = convertToBagItem(stockItem); //converts stockItem to bagItem before adding to bag
        shoppingBag.getBagItems().add(bagItem); // adds the now converted "bagItem" to the ShoppingBag
    }

    // Removes stockItem to ShoppingBag
    public void removeFromBag(BagItem bagItem) { // The added StockItems in ShoppingBag are already converted to bagItems so need to do it again.
        if (bagItem != null) { //Find and remove bagItem from ShoppingBag (only when there is a bagItem to remove, when it's not null)
            shoppingBag.getBagItems().remove(bagItem);
        }
    }
    // The added StockItems in ShoppingBag are already converted to bagItems, so no need to do it again.
    public void updateBagItemQuantity(BagItem bagItem, int quantity) {
        if (quantity <=0) {
            // If the quantity is less than or equal to 0, remove the product from the bag
            removeFromBag(bagItem);
        } else {
            // Missing a method to update the productQuantity when above 0
            bagItem.setBagItemQuantity(quantity);
        }

    }
    // Gets totalAmount, calculated in ShoppingBag
    public double getTotalAmount() {
        return shoppingBag.getTotalAmount();
    }

    // Method to print the items in the shopping bag
    public void printShoppingBagItems() {
        public List<BagItem> bagItems = shoppingBag.getBagItems();
        if (bagItems.isEmpty()) {
            System.out.println("Your shopping bag has no products yet. Continue Shopping!");
        } else {
            System.out.println("Items in your shopping bag:");
            for (BagItem bagItem : bagItems) {
                System.out.println("Product Name: " + bagItem.getProductName());
                System.out.println("Brand: " + bagItem.getProductBrand());
                System.out.println("Price: " + bagItem.getStockPrice());
                System.out.println("Color: " + bagItem.getStockColour());
                System.out.println("Size: " + bagItem.getStockSize());
                System.out.println("Quantity: " + bagItem.getBagItemQuantity());
            }
            System.out.println("Total Amount"+ getTotalAmount());
        }
    }
}
    // Method to Checkout()
    public void checkout(){
        double totalAmount = getTotalAmount() //access it from shoppingbag
        double shippingCost = shoppingBag.getShippingCost() //access it from shoppingbag
        System.out.println("Total Amount:" + totalAmount);
        System.out.println("Shipping Cost:" + shippingCost);
        System.out.println("Thank you for your order!");
    }
}
