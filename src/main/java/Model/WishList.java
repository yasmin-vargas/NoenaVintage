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
    private List<StockItem> wishlist; // Declare a wishlist of stockItems
    public WishList(){  //Initialize the "wishlist" in the WishList constructor
        wishlist = new ArrayList<>();
    }
    public List<StockItem> getWishListItems() {
        return wishlist;
    }

}

