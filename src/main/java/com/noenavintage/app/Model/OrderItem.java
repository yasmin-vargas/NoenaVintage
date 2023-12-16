package com.noenavintage.app.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemID;
    @ManyToOne
    @JoinColumn(name = "orderNumber")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "bagItemID")
    private BagItem bagItem;
    @Column(name = "bagItemQty")
    private int orderItemQty;

    public OrderItem() {  // Constructor
    }

    public OrderItem(Order order, BagItem bagItem, int orderItemQty) {
        this.order = order;
        this.bagItem = bagItem;
        this.orderItemQty = orderItemQty;
    }

    // Getters and setters
    public Long getOrderItemID() {return orderItemID;}
    public void setOrderItemID(Long orderItemID) {this.orderItemID = orderItemID;}
    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}
    public BagItem getBagItem() {return bagItem;}
    public void setBagItem(BagItem bagItem) {this.bagItem = bagItem;}
    public int getOrderItemQty() {return orderItemQty;}
    public void setOrderItemQty(int orderItemQty) {this.orderItemQty = orderItemQty;}
}
