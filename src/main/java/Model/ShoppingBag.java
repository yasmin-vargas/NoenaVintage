package Model;
import Repository.UserData;
import Model.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ShoppingBag")
public class ShoppingBag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bagID;
    private int bagItemQty;
    private BigDecimal totalAmount;
    private final static double SHIPPING_COST = 7.95; // Defines a constant shipping cost in EUR
    @ManyToOne
    @JoinColumn(name = "user_id")
    private long userID;

    @OneToMany(mappedBy = "shoppingBag", cascade = CascadeType.ALL)
    private List<BagItem> bagItems;

    // Constructor
    public ShoppingBag(long bagID, int bagItemQty, BigDecimal totalAmount, double SHIPPING_COST, long userID, List<BagItem> bagItems) {
        this.bagID = bagID;
        this.bagItemQty = bagItemQty;
        this.totalAmount = totalAmount;
        this.SHIPPING_COST = SHIPPING_COST;
        this.userID = userID;
        this.bagItems = bagItems;
    }

    // Getters and setters
    public long getBagID() {return bagID;}
    public void setBagID(long bagID) {this.bagID = bagID;}
    public long getBagItemQty() {return bagItemQty;}
    public void setBagItemQty(long bagItemQty) {this.bagItemQty = bagItemQty;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount = totalAmount;}
    public double getShippingCost() {return SHIPPING_COST;}
    public long getUserID() {return userID;}
    public void setUserID(long userID) {this.userID = userID;}
    public List<BagItem> getBagItems() {return bagItems;}
    public void setBagItems(List<BagItem> bagItems) {this.bagItems = bagItems;}
}