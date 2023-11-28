package Controller;
import Model.StockItem;
import Model.BagItem;
import Repository.BagItemData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/shopping-bag")
public class ShoppingBagController {  //Shopping Bag Controller Constructor
    private final static double SHIPPING_COST = 7.95; // Defines a constant shipping cost in EUR
    private static Logger log = LoggerFactory.getLogger(ShoppingBagController.class);
    @Autowired
    private BagItemData bagItemData;

    // Helper method to convert stockItems into bagItems
    private BagItem convertToBagItem (StockItem stockItem) {
        //Creates a new bagItem using information from the StockItem
        BagItem bagItem = new BagItem(
          1L, // Example value, provide the appropriate arguments
                  1L,
                  stockItem.getProductBrand(), //Getters from StockItem model
                  stockItem.getProductName(),
                  stockItem.getStockColour(),
                  stockItem.getStockSize(),
                  1,
                  stockItem.getStockPrice(),
                  stockItem
        );
        return bagItem;
    }
    @PostMapping("/add-to-bag")
    // Adds converted StockItems - BagItems to ShoppingBag
    public void addToBag(@RequestBody StockItem stockItem) {  //data model "StockItem"
        BagItem bagItem = convertToBagItem(stockItem); //converts stockItem to bagItem before adding to bag
        // Save the BagItem to the repository
        bagItemData.save(bagItem); // adds the now converted "bagItem" to the ShoppingBag
    }
    @DeleteMapping("/remove-from-bag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestBody BagItem bagItem) { // The added StockItems in ShoppingBag are already converted to bagItems so need to do it again.
        if (bagItem != null) { //Find and remove BagItem from ShoppingBag (only when there is a bagItem to remove, when it's not null)
            // Delete the BagItem from the repository
            bagItemData.deleteByBagItemID(bagItem.getBagItemID());
        }
    }
    @PutMapping("/update-bag-item-qty")
    // The added StockItems in ShoppingBag are already converted to bagItems, so no need to do it again.
    public void updateBagItemQty(@RequestBody Map<String, Object> updateInfo) {
        // Extract bagItem and bagItemQty from the request body
        Long bagItemID = (Long) updateInfo.get("BagItemID");
        Integer bagItemQty = (Integer) updateInfo.get("BagItemQty");

        // Find the bagItem in the BagItemData repository
        bagItemData.findByBagItemID(bagItemID)
            .ifPresent(bagItem -> {
                if (bagItemQty != null && bagItemQty > 0) {
                    bagItem.setBagItemQty(bagItemQty);
                    bagItemData.save(bagItem);  // Save the updated BagItem to the repository
                } else {
                    removeFromBag(bagItem);
                }
            });
    }
    // Get shipping cost
    public double getShippingCost() {return SHIPPING_COST;}

    // Method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    @GetMapping("/total-amount")
    public double getTotalAmount() {
        double totalAmount = 0.0; // Initialize variable "totalAmount" to 0.0
        // Iterate over bag items from the repository
        for (BagItem bagItem : bagItemData.findAll()) {
            totalAmount += bagItem.getBagItemPrice() * bagItem.getBagItemQty();
        }
        return totalAmount;
    }

    @GetMapping("/bag-items")
    // Method to get the BagItems in the ShoppingBag
    public List<Map<String, Object>> getBagItems() {
        List<Map<String, Object>> bagItemDetails = new ArrayList<>();
        List<BagItem> bagItems = bagItemData.findAll();
        if (bagItems.isEmpty()) {
            // Log a message or perform appropriate actions for an empty bag
            log.info("Your shopping bag has no products yet. Continue Shopping!");
        } else {
            // Convert BagItems to a format suitable for JSON response
            bagItemDetails = bagItems.stream()
                    .map(bagItem -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("productName", bagItem.getProductName());
                        map.put("productBrand", bagItem.getProductBrand());
                        map.put("bagItemPrice", bagItem.getBagItemPrice());
                        map.put("bagItemColour", bagItem.getBagItemColour());
                        map.put("bagItemSize", bagItem.getBagItemSize());
                        map.put("bagItemQty", bagItem.getBagItemQty());
                        return map;
                    })
                    .collect(Collectors.toList());
        }
        return bagItemDetails;
    }
}

