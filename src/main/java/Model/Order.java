package Model;
import Repository.OrderData;
import Repository.UserData;
import Model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import Model.BagItem;
import java.sql.Timestamp;

@Entity
@Table(name="CustomerOrder")
public class Order {
    @Id
    private long orderNumber;  // initializing object variables, that represent attributes of an order
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private int userID;
    @ManyToMany
    @JoinColumn(name = "billing_address_id")
    private int billingAddressID;
    @ManyToMany
    @JoinColumn(name = "shipping_address_id")
    private int shippingAddressID;

    private ArrayList<BagItem> selectedProducts; //Declare the ArrayList as a Class field with Product objects
    public Order() {  // Default constructor required by JPA
    }

    //Order Constructor
    public Order(long orderNumber, Timestamp orderDate, BigDecimal totalAmount, OrderStatusEnum orderStatus,int userID, int billingAddressID, int shippingAddressID, ArrayList<BagItem> selectedProducts){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.userID = userID;
        this.billingAddressID = billingAddressID;
        this.shippingAddressID = shippingAddressID;
        this.selectedProducts = selectedProducts; //Assign the provided ArrayList to the class field
    }

    // Getters and setters
    public long getOrderNumber() {return orderNumber;}
    public void setOrderNumber(long orderNumber) {this.orderNumber = orderNumber;}
    public Timestamp getOrderDate() {return orderDate;}
    public void setOrderDate(Timestamp orderDate) {this.orderDate = orderDate;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount = totalAmount;}
    public OrderStatusEnum getOrderStatus() {return orderStatus;}
    public void setOrderStatus(OrderStatusEnum orderStatus) {this.orderStatus = orderStatus;}
    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public int getBillingAddressID() {return billingAddressID;}
    public void setBillingAddressID(int billingAddressID) {this.billingAddressID = billingAddressID;}
    public int getShippingAddressID() {return shippingAddressID;}
    public void setShippingAddressID(int shippingAddressID) {this.shippingAddressID = shippingAddressID;}
    public List<BagItem> getSelectedProducts() {return selectedProducts;}
    public void setSelectedProducts(ArrayList<BagItem> selectedProducts) {this.selectedProducts = selectedProducts;}
}

