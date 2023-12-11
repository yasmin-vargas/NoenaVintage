package com.noenavintage.app.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "ReturnItem")
public class ReturnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnItemID;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    // Constructor
    public ReturnItem() {
    }
    // Constructor
    public ReturnItem(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    // Getters and setters
    public Long getReturnItemID() {return returnItemID;}
    public void setReturnItemID(Long returnItemID) {this.returnItemID = returnItemID;}
    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}

}
