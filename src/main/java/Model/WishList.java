package Model;
import Model.StockItem;
import java.util.ArrayList;
import java.util.List;
public class WishList {
    private List<StockItem> wishlist; // Declare a wishlist of stockItems

    public WishList(){  //Initialize the "wishlist" in the WishList constructor
        wishlist = new ArrayList();
    }

    public List<StockItem> getWishListItems() {
        return wishlist;
    }

}

