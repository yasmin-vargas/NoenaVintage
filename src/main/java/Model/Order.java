package Model;
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
    private long orderNumber;  // initializing object variables, that represent attributes of an order
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    @ManyToOne
    @JoinColumn(name = "userID")
    private long userID;

    @ManyToOne
    @JoinColumn(name = "addressID")
    private long addressID;

    private ArrayList<StockItem> selectedProducts; //Declare the ArrayList as a Class field with Product objects
    public Order() {  // Default constructor required by JPA
    }

    //Order Constructor
    public Order(long orderNumber, Timestamp orderDate, BigDecimal totalAmount, OrderStatusEnum orderStatus,long userID, long addressID, ArrayList<StockItem> selectedProducts){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.userID = userID;
        this.addressID = addressID;
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
    public long getUserID() {return userID;}
    public void setUserID(long userID) {this.userID = userID;}
    public long getAddressID() {return addressID;}
    public void setAddressID(long billingAddressID) {this.addressID = addressID;}
    public List<StockItem> getSelectedProducts() {return selectedProducts;}
    public void setSelectedProducts(ArrayList<StockItem> selectedProducts) {this.selectedProducts = selectedProducts;}
}

