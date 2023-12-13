package com.noenavintage.app.Model;
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
    @JoinColumn(name = "userID")
    private User user;
    private int totalItemQty;
    private BigDecimal totalAmount;
    @OneToMany(mappedBy = "shoppingBag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BagItem> bagItems = new ArrayList<>();
    public ShoppingBag() {  // Default constructor for JPA or other frameworks
    }

    // Constructor
    public ShoppingBag(User user, int totalItemQty, BigDecimal totalAmount) {
        this.user = user;
        this.totalItemQty = 0;
        this.totalAmount = BigDecimal.ZERO;
    }

    //Getters and setters
    public Long getBagID() {return bagID;}
    public void setBagID(Long bagID) {this.bagID = bagID;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public int getTotalItemQty() {return totalItemQty;}
    public void setTotalItemQty(int totalItemQty) {this.totalItemQty = totalItemQty;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount = totalAmount;}
    public List<BagItem> getBagItems() {return bagItems;}
    public void setBagItems(List<BagItem> bagItems) {this.bagItems = bagItems;}
}

