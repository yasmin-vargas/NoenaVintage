package controller;
import Model.StockItem;
import Model.WishList;
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