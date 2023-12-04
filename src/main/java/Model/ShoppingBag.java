package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
@Entity
public class ShoppingBag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bagID;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal totalAmount;
    @OneToMany(mappedBy = "shoppingBag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> bagItems = new ArrayList<>();

    //Getters and setters
    public Long getBagID() {return bagID;}
    public void setBagID(Long bagID) {this.bagID = bagID;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount = totalAmount;}
    public List<Product> getBagItems() {return bagItems;}
    public void setBagItems(List<Product> bagItems) {this.bagItems = bagItems;}
}

