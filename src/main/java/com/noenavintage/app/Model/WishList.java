package com.noenavintage.app.Model;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="WishList")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListItem> wishListItems; // Declare a wishlist of wishlist items
    public WishList() {  // Default constructor required by JPA
    }

    // Constructor
    public WishList(User user, List<WishListItem> wishListItems) {
        this.user = user;
        this.wishListItems = wishListItems;
    }
    // Getters and setters
    public Long getWishListID() {return wishListID;}
    public void setWishListID(long wishListID) {this.wishListID = wishListID;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public List<WishListItem> getWishListItems() {return wishListItems;}
    public void setWishListItems(List<WishListItem> wishListItems) {this.wishListItems = wishListItems;}
}

