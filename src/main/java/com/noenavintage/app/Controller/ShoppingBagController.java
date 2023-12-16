package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.ShoppingBag;
import com.noenavintage.app.Model.BagItem;
import com.noenavintage.app.Repository.ShoppingBagData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Collections;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Objects;

@RestController
@RequestMapping("/shoppingbag")
public class ShoppingBagController {
    @Autowired
    private ShoppingBagData shoppingBagData;
    private final static BigDecimal SHIPPING_COST = new BigDecimal("7.95"); // Defines as example constant shipping cost in EUR
    private static Logger log = LoggerFactory.getLogger(ShoppingBagController.class);

    // Method to get the BagItems in the ShoppingBag
    @GetMapping("/bagItems")
    public List<BagItem> getBagItems(@RequestBody User user) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null) {
            List<BagItem> bagItems = shoppingBag.getBagItems();
            if (!bagItems.isEmpty()) {
                return new ArrayList<>(bagItems);
            } else {
                log.info("Your shopping bag has no products yet. Continue Shopping!");
            }
        } else {
            log.info("Shopping bag not found for user: {}", user);
        }
        return Collections.emptyList(); // Return an empty list if there are no bag items or shopping bag not found
    }

    // Get shipping cost
    @GetMapping("/shippingcost")
    public BigDecimal getShippingCost() {return SHIPPING_COST;}

    @GetMapping("/totalamount")
    public BigDecimal getTotalAmount(@RequestBody User user) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null) {
            return calculateTotalAmount(shoppingBag);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for user: {} " + user);
        }
    }

    // Helper method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    private BigDecimal calculateTotalAmount(ShoppingBag shoppingBag) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        for (BagItem bagItem : shoppingBag.getBagItems()) {
            BigDecimal itemTotal = bagItem.getProduct().getProductPrice()
                    .multiply(BigDecimal.valueOf(bagItem.getBagItemQty()));
            totalAmount = totalAmount.add(itemTotal);
        }
        return totalAmount;
    }

    @PostMapping("/addToBag")
    // Adds Products or Variants to ShoppingBag
    public void addToBag(@RequestBody User user, @RequestBody Map<String, Object> request) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if(shoppingBag !=null) {
            Product product = (Product) request.get("product");
            int bagItemQty = (int) request.get("bagItemQty");

            if (product != null && bagItemQty > 0) {
                BagItem bagItem = new BagItem(shoppingBag, product, bagItemQty);
                shoppingBag.getBagItems().add(bagItem);
                shoppingBagData.save(shoppingBag);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product or quantity.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for user: " + user);
        }
}

    @PutMapping("/updateBagItemQty")
    public void updateBagItemQty(@RequestBody User user, @RequestBody Map<String, Object> bagItem) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null) {
            // Extract productID and bagItemQty from the request body
            Long bagItemID = (Long) bagItem.get("BagItemID");
            Integer bagItemQty = (Integer) bagItem.get("BagItemQty");

            // Find the BagItem in the ShoppingBag
            Optional<BagItem> optionalBagItem = shoppingBag.getBagItems().stream()
                .filter(item -> item.getBagItemID().equals(bagItemID))
                .findFirst();

            if (optionalBagItem.isPresent()) {
            BagItem bagItemEntity = optionalBagItem.get();
               if (bagItemQty != null && bagItemQty > 0) {
                bagItemEntity.setBagItemQty(bagItemQty);
                updateTotalBagQty(user);  // Update totalBagQty based on the changes in bag items with the userID parameter
               } else {
                removeFromBag(user, bagItemID);
               }
            } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found in the shopping bag with bagItemID: " + bagItemID);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for user: " + user);
        }
    }

    // Method to update totalBagQty based on the bagItems
    private void updateTotalBagQty(User user) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        int totalBagQty = shoppingBag.getBagItems().stream()
                .mapToInt(BagItem::getBagItemQty)
                .sum();
        shoppingBag.setTotalItemQty(totalBagQty);
    }

    @DeleteMapping("/removefrombag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestBody User user, @RequestParam Long bagItemID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null) { //Find and remove product from ShoppingBag (only when there is a product to remove, when it's not null)
            shoppingBag.getBagItems().removeIf(bagItem -> Objects.equals(bagItem.getBagItemID(), bagItemID));
            shoppingBagData.save(shoppingBag); // Save the updated shopping bag
            updateTotalBagQty(user); // Update totalBagQty after removing the product
        }
    }
    public void clearShoppingBag(@RequestBody User user) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null) {
            shoppingBag.getBagItems().clear();
            shoppingBagData.save(shoppingBag);
        }
    }
}

