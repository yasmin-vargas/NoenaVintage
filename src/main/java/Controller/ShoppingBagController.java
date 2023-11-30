package Controller;
import Model.StockItem;
import Repository.StockItemData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/shopping-bag")
public class ShoppingBagController {  //Shopping Bag Controller Constructor
    private final static BigDecimal SHIPPING_COST = new BigDecimal("7.95"); // Defines a constant shipping cost in EUR
    private static Logger log = LoggerFactory.getLogger(ShoppingBagController.class);
    @Autowired
    private StockItemData stockItemData;

    @PostMapping("/add-to-bag")
    // Adds StockItems to ShoppingBag
    public void addToBag(@RequestBody StockItem stockItem) {
        // Save the BagItem to the repository
        stockItemData.save(stockItem); // adds the now converted "bagItem" to the ShoppingBag
    }
    @DeleteMapping("/remove-from-bag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestBody StockItem stockItem) {
        if (stockItem != null) { //Find and remove StockItem from ShoppingBag (only when there is a stockItem to remove, when it's not null)
            // Delete the StockItem from the repository
            stockItemData.deleteByStockID(stockItem.getStockID());
        }
    }
    @PutMapping("/update-stockQty")
    // The added StockItems in ShoppingBag are already converted to bagItems, so no need to do it again.
    public void updateStockQty(@RequestBody Map<String, Object> updateInfo) {
        // Extract stockItem and stockQty from the request body
        Long stockID = (Long) updateInfo.get("StockID");
        Integer stockQty = (Integer) updateInfo.get("StockQty");

        // Find the stockItem in the StockItemData repository
        Optional<StockItem> optionalStockItem = stockItemData.findByStockID(stockID);
        if (optionalStockItem.isPresent()) {
            StockItem stockItem = optionalStockItem.get();
            if (stockQty != null && stockQty > 0) {
                stockItem.setStockQty(stockQty);
                stockItemData.save(stockItem);  // Save the updated StockItem to the repository
            } else {
                removeFromBag(stockItem);
            }
        } else {  // Handle the case when the StockItem with the given stockID is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StockItem not found with stockID: " + stockID);
        }
    }

    // Get shipping cost
    public BigDecimal getShippingCost() {return SHIPPING_COST;}

    // Method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    @GetMapping("/total-amount")
    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0); // Initialize variable "totalAmount" to 0.0
        // Iterate over bag items from the repository
        for (StockItem stockItem : stockItemData.findAll()) {
            BigDecimal itemTotal = stockItem.getStockPrice().multiply(BigDecimal.valueOf(stockItem.getStockQty()));
            totalAmount = totalAmount.add(itemTotal);
        }
        return totalAmount;
    }

    @GetMapping("/bag-items")
    // Method to get the BagItems in the ShoppingBag
    public List<Map<String, Object>> getBagItems() {
        List<Map<String, Object>> stockItemDetails = new ArrayList<>();
        List<StockItem> stockItems = stockItemData.findAll();
        if (stockItems.isEmpty()) {
            // Log a message or perform appropriate actions for an empty bag
            log.info("Your shopping bag has no products yet. Continue Shopping!");
        } else {
            // Convert StockItems to a format suitable for JSON response
            stockItemDetails = stockItems.stream()
                    .map(bagItem -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("productName", bagItem.getProductName());
                        map.put("productBrand", bagItem.getProductBrand());
                        map.put("stockPrice", bagItem.getStockPrice());
                        map.put("stockColour", bagItem.getStockColour());
                        map.put("stockSize", bagItem.getStockSize());
                        map.put("stockQty", bagItem.getStockQty());
                        return map;
                    })
                    .collect(Collectors.toList());
        }
        return stockItemDetails;
    }
}

