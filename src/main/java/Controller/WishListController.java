package controller;
import Model.StockItem;
import Model.WishList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
public class WishListController {
    private WishList wishList;
    public WishListController(WishList wishList) {
        this.wishList = wishList;
    }

    // Adds stockItems to WishList
    public void addToWishList (StockItem stockItem){
        wishList.getWishListItems().add(stockItem);
    }

    //Removes stockItems from WishList
    public void removeFromWishList (StockItem stockItem){
        if (stockItem != null)
            wishList.getWishListItems().remove(stockItem);
    }
}