package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.*;
import com.noenavintage.app.Repository.WishListData;
import com.noenavintage.app.Repository.UserData;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.Product;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import java.util.Optional;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/wishlists")
public class WishListController {
    @Autowired
    private WishListData wishListData;
    @Autowired
    private UserData userData;
    private static final Logger log = LoggerFactory.getLogger(WishListController.class);
    @Autowired
    public WishListController() {
    }

    // Method to get the WishlistItems for a specific user
    @GetMapping("/getWishListItems/{userID}")
    public List<WishListItem> getWishListItems(@PathVariable Long userID) {
        // Retrieve the user by ID
        Optional<User> userOptional = userData.findByUserID(userID);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user != null) {  // Retrieve the WishList for the user
                WishList wishList = wishListData.findWishListByUser(user);

                if (wishList != null) {
                    List<WishListItem> wishListItems = wishList.getWishListItems();
                    if (!wishListItems.isEmpty()) {
                        return new ArrayList<>(wishListItems);
                    } else {
                        log.info("Your wishlist has no products yet. Add products to your wishlist!");
                    }
                } else {
                    log.info("Wishlist not found for user with ID: {}", userID);
                }
            } else {
                log.info("User not found for ID: {}", userID);
            }
        }
        return Collections.emptyList(); // Return an empty list if there are no wishlist items or user not found
    }

    @PostMapping("/addToWishList/{productID}")
    // Adds products to WishList
    public void addToWishList (@RequestBody User user, @RequestBody Map < String, Object > request){
         WishList wishList = wishListData.findWishListByUser(user);

         if (wishList != null) {
              Product product = (Product) request.get("product");
              int wishListItemQty = (int) request.get("wishListItemQty");

              if (product != null && wishListItemQty > 0) {
                   WishListItem wishListItem = new WishListItem(wishList, product, wishListItemQty);
                   wishList.getWishListItems().add(wishListItem);
                   wishListData.save(wishList);
              } else {
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product or quantity.");
              }
         } else {
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wishlist not found for user: " + user);
         }
    }

    @DeleteMapping("/removeFromWishList")
    //Removes products from WishList
    public void removeFromWishList (@RequestBody User user, @RequestParam Long productID){
         WishList wishList = wishListData.findWishListByUser(user);
         if (wishList != null) {
             wishList.getWishListItems().removeIf(item -> Objects.equals(item.getProduct().getProductID(), productID));
             wishListData.save(wishList);
         }
    }
}