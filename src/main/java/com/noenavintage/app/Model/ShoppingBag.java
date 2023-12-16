package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.sql.Timestamp;
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
    private Timestamp bagDate;
    @OneToMany(mappedBy = "shoppingBag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BagItem> bagItems = new ArrayList<>();
    public ShoppingBag() {  // Empty constructor for JPA
    }

    public ShoppingBag(User user, int totalItemQty, BigDecimal totalAmount, Timestamp bagDate, List<BagItem> bagItems) {
        this.user = user;
        this.totalItemQty = totalItemQty;
        this.totalAmount = totalAmount;
        this.bagDate = bagDate;
        this.bagItems = (bagItems != null) ? bagItems : new ArrayList<>();
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
    public Timestamp getBagDate() {return bagDate;}
    public void setBagDate(Timestamp bagDate) {this.bagDate = bagDate;}
    public List<BagItem> getBagItems() {return bagItems;}
    public void setBagItems(List<BagItem> bagItems) {this.bagItems = bagItems;}
}

