package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

@Entity
@Table(name="´Order´")
public class Order {
    @Id
    private Long orderNumber;  // initializing object variables, that represent attributes of an order
    private Timestamp orderDate;
    private int itemQty;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "billingAddressID", nullable = false)
    private Address billingAddress;
    @ManyToOne
    @JoinColumn(name = "shippingAddressID")
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems; //Declare the ArrayList as a Class field with Product objects
    public Order() {  // Default constructor required by JPA
        this.orderItems = new ArrayList<>();
    }

    //Order Constructor
    public Order(Timestamp orderDate, int itemQty, BigDecimal totalAmount, OrderStatusEnum orderStatus, User user, Address billingAddress, Address shippingAddress, List <OrderItem> orderItems){
        this.orderDate = orderDate;
        this.itemQty = itemQty;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.user = user;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.orderItems = orderItems;
    }

    // Getters and setters
    public long getOrderNumber() {return orderNumber;}
    public void setOrderNumber(long orderNumber) {this.orderNumber = orderNumber;}
    public Timestamp getOrderDate() {return orderDate;}
    public void setOrderDate(Timestamp orderDate) {this.orderDate = orderDate;}
    public int getItemQty() {return itemQty;}
    public void setItemQty(int itemQty) {this.itemQty = itemQty;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount = totalAmount;}
    public OrderStatusEnum getOrderStatus() {return orderStatus;}
    public void setOrderStatus(OrderStatusEnum orderStatus) {this.orderStatus = orderStatus;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Address getBillingAddress() {return billingAddress;}
    public void setBillingAddress(Address billingAddress) {this.billingAddress = billingAddress;}
    public Address getShippingAddress() {return shippingAddress;}
    public void setShippingAddress(Address shippingAddress) {this.shippingAddress = shippingAddress;}
    public List<OrderItem> getOrderItems() {return orderItems;}
    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}

}

