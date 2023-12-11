package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
public class BagItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bagItemID;
    @ManyToOne
    @JoinColumn(name = "bagID")
    private ShoppingBag shoppingBag;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "variantID")
    private Variant variant;

    private int bagItemQty;
    // Constructor
    public BagItem() {
    }

    // Constructor for a simple product
    public BagItem(ShoppingBag shoppingBag, Product product, int bagItemQty) {
        this.shoppingBag = shoppingBag;
        this.product = product;
        this.bagItemQty = bagItemQty;
    }

    // Constructor for a variant
    public BagItem(ShoppingBag shoppingBag, Variant variant, int bagItemQty) {
        this.shoppingBag = shoppingBag;
        this.variant = variant;
        this.bagItemQty = bagItemQty;
    }

    // Getters and setters
    public Long getBagItemID() {return bagItemID;}
    public void setBagItemID(Long bagItemID) {this.bagItemID = bagItemID;}
    public ShoppingBag getShoppingBag() {return shoppingBag;}
    public void setShoppingBag(ShoppingBag shoppingBag) {this.shoppingBag = shoppingBag;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public int getBagItemQty() {return bagItemQty;}
    public void setBagItemQty(int bagItemQty) {this.bagItemQty = bagItemQty;}
}
