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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long wishListID;
    long userID;
    private List<StockItem> wishList; // Declare a wishlist of stockItems
    public WishList() {  // Default constructor required by JPA
    }

    // Constructor
    public WishList(long wishListID, long userID, List<StockItem> wishList) {
        this.wishListID = wishListID;
        this.userID = userID;
        this.wishList = wishList;
    }
    // Getters and setters
    public long getWishListID() {return wishListID;}
    public void setWishListID(long wishListID) {this.wishListID = wishListID;}
    public long getUserID() {return userID;}
    public void setUserID(long userID) {this.userID = userID;}
    public List<StockItem> getWishListItems() {return wishList;}
    public void setWishListItems(List<StockItem> wishList) {this.wishList = wishList;}
}

