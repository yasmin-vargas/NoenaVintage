package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Model.ShoppingBag;
import com.noenavintage.app.Model.BagItem;
import com.noenavintage.app.Repository.ProductData;
import com.noenavintage.app.Repository.ShoppingBagData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Collections;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Objects;

@RestController
@RequestMapping("/shoppingbag")
public class ShoppingBagController {  //Shopping Bag Controller Constructor
    @Autowired
    private ShoppingBagData shoppingBagData;
    @Autowired
    private ProductData productData;
    private final static BigDecimal SHIPPING_COST = new BigDecimal("7.95"); // Defines as example constant shipping cost in EUR
    private static Logger log = LoggerFactory.getLogger(ShoppingBagController.class);

    @GetMapping("/bagitems")
    // Method to get the BagItems in the ShoppingBag
    public List<BagItem> getBagItems(@RequestParam Long userID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        if (shoppingBag != null) {
            List<BagItem> bagItems = shoppingBag.getBagItems();
            if (!bagItems.isEmpty()) {
                return bagItems;
            } else {
                log.info("Your shopping bag has no products yet. Continue Shopping!");
            }
        } else {
            log.info("Shopping bag not found for userID: {}", userID);
        }
        return Collections.emptyList(); // Return an empty list if there are no bag items or shopping bag not found
    }

    // Get shipping cost
    @GetMapping("/shippingcost")
    public BigDecimal getShippingCost() {return SHIPPING_COST;}

    @GetMapping("/totalamount")
    public BigDecimal getTotalAmount(@RequestParam Long userID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        if (shoppingBag != null) {
            return calculateTotalAmount(shoppingBag);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for userID: " + userID);
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

    @PostMapping("/addtobag")
    // Adds Products or Variants to ShoppingBag
    public void addToBag(@RequestParam Long userID, @RequestBody Map<String, Object> request) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for userID: " + userID);
        }
}

    @PutMapping("/updateBagItemQty")
    public void updateBagItemQty(@RequestParam Long userID, @RequestBody Map<String, Object> bagItem) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
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
                updateTotalBagQty(userID);  // Update totalBagQty based on the changes in bag items with the userID parameter
               } else {
                removeFromBag(userID, bagItemID);
               }
            } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found in the shopping bag with bagItemID: " + bagItemID);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping bag not found for userID: " + userID);
        }
    }

    // Method to update totalBagQty based on the bagItems
    private void updateTotalBagQty(Long userID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        int totalBagQty = shoppingBag.getBagItems().stream()
                .mapToInt(BagItem::getBagItemQty)
                .sum();
        shoppingBag.setTotalBagQty(totalBagQty);
    }

    @DeleteMapping("/removefrombag")
    // Removes BagItem to ShoppingBag
    public void removeFromBag(@RequestParam Long userID, @RequestParam Long bagItemID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        if (shoppingBag != null) { //Find and remove product from ShoppingBag (only when there is a product to remove, when it's not null)
            shoppingBag.getBagItems().removeIf(bagItem -> Objects.equals(bagItem.getBagItemID(), bagItemID));
            shoppingBagData.save(shoppingBag); // Save the updated shopping bag
            updateTotalBagQty(userID); // Update totalBagQty after removing the product
        }
    }
    public void clearShoppingBag(Long userID) {
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        if (shoppingBag != null) {
            shoppingBag.getBagItems().clear();
            shoppingBagData.save(shoppingBag);
        }
    }
}

