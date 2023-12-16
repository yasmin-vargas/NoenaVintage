package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.WishList;
import com.noenavintage.app.Repository.WishListData;
import com.noenavintage.app.Model.WishListItem;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/wishlists")
public class WishListController {
    @Autowired
    private WishListData wishListData;
    private static final Logger log = LoggerFactory.getLogger(WishListController.class);
    @Autowired
    public WishListController() {
    }

    // Method to get the WishlistItems in the Wishlist
    @GetMapping("/wishListItems")
    public List<WishListItem> getWishListItems(@RequestBody User user) {
        WishList wishList = wishListData.findWishListByUser(user);
        if (wishList != null) {
            List<WishListItem> wishListItems = wishList.getWishListItems();
            if (!wishListItems.isEmpty()) {
                return new ArrayList<>(wishListItems);
            } else {
                log.info("Your wishlist has no products yet. Add products to your wishlist!");
            }
        } else {
            log.info("Wishlist not found for user: {}", user);
        }
        return Collections.emptyList(); // Return an empty list if there are no wishlist items or wishlist not found
    }
    @PostMapping("/addToWishList")
    // Adds products to WishList
    public void addToWishList(@RequestBody User user, @RequestBody Map<String, Object> request) {
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
    public void removeFromWishList(@RequestBody User user, @RequestParam Long productId) {
        WishList wishList = wishListData.findWishListByUser(user);
        if (wishList != null) {
            wishList.getWishListItems().removeIf(item -> Objects.equals(item.getProduct().getProductID(), productId));
            wishListData.save(wishList);
        }
    }
}