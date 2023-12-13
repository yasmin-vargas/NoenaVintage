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

    public OrderItem() {  // Constructor
    }

    public OrderItem(Order order, BagItem bagItem) {
        this.order = order;
        this.bagItem = bagItem;
    }

    // Getters and setters
    public Long getOrderItemID() {return orderItemID;}
    public void setOrderItemID(Long orderItemID) {this.orderItemID = orderItemID;}
    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}
    public BagItem getBagItem() {return bagItem;}
    public void setBagItem(BagItem bagItem) {this.bagItem = bagItem;}
}
