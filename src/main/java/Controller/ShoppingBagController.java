package Controller;
import Model.Product;
import Repository.ProductData;
import Repository.ShoppingBagData;
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
    @Autowired
    private ShoppingBagData shoppingBagData;
    private final static BigDecimal SHIPPING_COST = new BigDecimal("7.95"); // Defines a constant shipping cost in EUR
    private static Logger log = LoggerFactory.getLogger(ShoppingBagController.class);
    @Autowired
    private ProductData productData;

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
    public List<Map<String, Object>> getBagItems(@RequestParam Long userId) {
        List<Map<String, Object>> productDetails = new ArrayList<>();

        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userId);

        if (shoppingBag != null && !shoppingBag.getProducts().isEmpty()) {
            productDetails = shoppingBag.getProducts().stream()
                    .map(product -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("productName", product.getProductName());
                        map.put("productBrand", product.getProductBrand());
                        map.put("productDescription", product.getProductDescription());
                        map.put("productPrice", product.getProductPrice());
                        map.put("productColour", product.getProductColour());
                        map.put("productSize", product.getProductSize());
                        map.put("productQty", product.getProductQty());
                        return map;
                    })
                    .collect(Collectors.toList());
        } else {
            log.info("Your shopping bag has no products yet. Continue Shopping!");
        }
        return productDetails;
    }
    @PostMapping("/add-to-bag")
    // Adds StockItems to ShoppingBag
    public void addToBag(@RequestBody Product product) {
        productData.save(product);
    }
    @PutMapping("/update-bagItemQty")
    public void updateBagItemQty(@RequestBody Map<String, Object> updateInfo) {
        // Extract productID and bagItemQty from the request body
        Long productID = (Long) updateInfo.get("ProductID");
        Integer bagItemQty = (Integer) updateInfo.get("BagItemQty");

        // Find the product in the ProductData repository
        Optional<Product> optionalProduct = shoppingBag.getProducts().stream()
                .filter(product -> product.getProductID().equals(productID))
                .findFirst();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (bagItemQty != null && bagItemQty > 0) {
                product.setBagItemQty(bagItemQty);
            } else {
                removeFromBag(product);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in the shopping bag with productID: " + productID);
        }
    }
    @DeleteMapping("/remove-from-bag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestBody Product product) {
        if (product != null) { //Find and remove product from ShoppingBag (only when there is a product to remove, when it's not null)
            productData.deleteByProductID(product.getProductID()); // Delete the product from the repository
        }
    }

}

