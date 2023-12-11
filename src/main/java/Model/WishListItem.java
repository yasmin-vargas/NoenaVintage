package Model;
import jakarta.persistence.*;

@Entity
@Table(name = "WishListItem")
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListItemID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    // Constructor
    public WishListItem() {
    }
    // Constructor
    public WishListItem(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    // Getters and setters
    public Long getWishListItemID() {return wishListItemID;}

    public void setWishListItemID(Long wishListItemID) {this.wishListItemID = wishListItemID;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}
}
