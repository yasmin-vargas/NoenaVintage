package Model;
import Repository.OrderData;
import Repository.UserData;
import Model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
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
    private double orderAmount;
    private int customerID;
    private int billingAddressID;
    private int shippingAddressID;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    private ArrayList<BagItem> selectedProducts; //Declare the ArrayList as a Class field with Product objects
    public Order() {
        // Default constructor required by JPA
    }
    //Order Constructor
    public Order(long orderNumber, Timestamp orderDate, double orderAmount, int customerID, int billingAddressID, int shippingAddressID, OrderStatusEnum orderStatus, ArrayList<BagItem> selectedProducts){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.customerID = customerID;
        this.billingAddressID = billingAddressID;
        this.shippingAddressID = shippingAddressID;
        this.orderStatus = orderStatus;
        this.selectedProducts = selectedProducts; //Assign the provided ArrayList to the class field
    }

    // Getters and setters
    public long getOrderNumber() {return orderNumber;}

    public void setOrderNumber(long orderNumber) {this.orderNumber = orderNumber;}

    public Timestamp getOrderDate() {return orderDate;}

    public void setOrderDate(Timestamp orderDate) {this.orderDate = orderDate;}

    public double getOrderAmount() {return orderAmount;}

    public void setOrderAmount(double orderAmount) {this.orderAmount = orderAmount;}

    public int getCustomerID() {return customerID;}

    public void setCustomerID(int customerID) {this.customerID = customerID;}

    public int getBillingAddressID() {return billingAddressID;}

    public void setBillingAddressID(int billingAddressID) {this.billingAddressID = billingAddressID;}

    public int getShippingAddressID() {return shippingAddressID;}

    public void setShippingAddressID(int shippingAddressID) {this.shippingAddressID = shippingAddressID;}

    public OrderStatusEnum getOrderStatus() {return orderStatus;}

    public void setOrderStatus(OrderStatusEnum orderStatus) {this.orderStatus = orderStatus;}

    public List<BagItem> getSelectedProducts() {return selectedProducts;}

    public void setSelectedProducts(ArrayList<BagItem> selectedProducts) {this.selectedProducts = selectedProducts;}
}

