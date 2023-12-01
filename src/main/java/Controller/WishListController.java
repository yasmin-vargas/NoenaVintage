package controller;
import Model.Product;
import Model.WishList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    private WishList wishList;
    public WishListController(WishList wishList) {
        this.wishList = wishList;
    }

    @PostMapping("/add-to-wishlist")
    // Adds stockItems to WishList
    public void addToWishList (Product product){
        wishList.getWishListItems().add(product);
    }

    @DeleteMapping("/remove-from-wishlist")
    //Removes stockItems from WishList
    public void removeFromWishList (Product product){
        if (product != null)
            wishList.getWishListItems().remove(product);
    }
}