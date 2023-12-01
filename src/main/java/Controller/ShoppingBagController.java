package Controller;
import Model.Product;
import Repository.ProductData;
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
    private ProductData productData;

    @PostMapping("/add-to-bag")
    // Adds StockItems to ShoppingBag
    public void addToBag(@RequestBody Product product) {
        productData.save(product);
    }
    @DeleteMapping("/remove-from-bag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestBody Product product) {
        if (product != null) { //Find and remove product from ShoppingBag (only when there is a product to remove, when it's not null)
            productData.deleteByProductID(product.getProductID()); // Delete the product from the repository
        }
    }
    @PutMapping("/update-stockQty")
    // The added StockItems in ShoppingBag are already converted to bagItems, so no need to do it again.
    public void updateStockQty(@RequestBody Map<String, Object> updateInfo) {
        // Extract stockItem and stockQty from the request body
        Long productID = (Long) updateInfo.get("ProductID");
        Integer productQty = (Integer) updateInfo.get("ProductQty");

        // Find the stockItem in the StockItemData repository
        Optional<Product> optionalStockItem = productData.findByProductID(productID);
        if (optionalStockItem.isPresent()) {
            Product product = optionalStockItem.get();
            if (productQty != null && productQty > 0) {
                product.setProductQty(productQty);
                productData.save(product);  // Save the updated Product to the repository
            } else {
                removeFromBag(product);
            }
        } else {  // Handle the case when the Product with the given productID/SKU nr. is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with productID: " + productID);
        }
    }

    // Get shipping cost
    public BigDecimal getShippingCost() {return SHIPPING_COST;}

    // Method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    @GetMapping("/total-amount")
    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0); // Initialize variable "totalAmount" to 0.0
        // Iterate over bag items from the repository
        for (Product product : productData.findAll()) {
            BigDecimal itemTotal = product.getProductPrice().multiply(BigDecimal.valueOf(product.getProductQty()));
            totalAmount = totalAmount.add(itemTotal);
        }
        return totalAmount;
    }

    @GetMapping("/bag-items")
    // Method to get the BagItems in the ShoppingBag
    public List<Map<String, Object>> getBagItems() {
        List<Map<String, Object>> productDetails = new ArrayList<>();
        List<Product> products = productData.findAll();
        if (products.isEmpty()) {
            // Log a message or perform appropriate actions for an empty bag
            log.info("Your shopping bag has no products yet. Continue Shopping!");
        } else {
            // Convert Products to a format suitable for JSON response
            productDetails = products.stream()
                    .map(bagItem -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("productName", bagItem.getProductName());
                        map.put("productBrand", bagItem.getProductBrand());
                        map.put("productDescription", bagItem.getProductDescription());
                        map.put("productPrice", bagItem.getProductPrice());
                        map.put("productColour", bagItem.getProductColour());
                        map.put("productSize", bagItem.getProductSize());
                        map.put("productQty", bagItem.getProductQty());
                        return map;
                    })
                    .collect(Collectors.toList());
        }
        return productDetails;
    }
}

