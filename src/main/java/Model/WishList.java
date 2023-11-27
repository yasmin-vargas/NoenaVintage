package Model;
import Model.StockItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="WishList")
public class WishList {
    long wishListID;
    long userID;
    private List<StockItem> wishList; // Declare a wishlist of stockItems

    // Constructor
    public WishList(long wishListID, long userID, List<StockItem> wishList) {
        this.wishListID = wishListID;
        this.userID = userID;
        this.wishList = wishList;
    }
    // Getters and setters
    public List<StockItem> getWishListItems() {
        return wishList;
    }

}

