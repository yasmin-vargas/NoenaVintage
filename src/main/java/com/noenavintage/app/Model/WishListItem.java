package com.noenavintage.app.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "WishListItem")
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListItemID;
    @ManyToOne
    @JoinColumn(name = "wishlistID")
    private WishList wishList;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "variantID")
    private Variant variant;
    private int wishListItemQty;
    public WishListItem() {    // Constructor
    }
    // Constructor for a simple product
    public WishListItem(WishList wishList, Product product, int wishListItemQty) {
        this.wishList = wishList;
        this.product = product;
        this.wishListItemQty = wishListItemQty;
    }

    // Constructor for a variant
    public WishListItem(WishList wishList, Variant variant, int wishListItemQty) {
        this.wishList = wishList;
        this.variant = variant;
        this.wishListItemQty = wishListItemQty;
    }

    // Getters and setters
    public Long getWishListItemID() {return wishListItemID;}
    public void setWishListItemID(Long wishListItemID) {this.wishListItemID = wishListItemID;}
    public WishList getWishList() {return wishList;}
    public void setWishList(WishList wishList) {this.wishList = wishList;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public Variant getVariant() {return variant;}
    public void setVariant(Variant variant) {this.variant = variant;}
    public int getWishListItemQty() {return wishListItemQty;}
    public void setWishListItemQty(int wishListItemQty) {this.wishListItemQty = wishListItemQty;}
}
