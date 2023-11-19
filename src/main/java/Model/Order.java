package Model;
import java.util.ArrayList;
@Entity
public class Order {
    private int orderID;  // initializing object variables, that represent attributes of an order
    private int customerID;
    private double totalAmount;
    private double totalShipping;
    private String orderDate;
    private String dispatchDate;
    private int shippingID;
    private OrderStatusEnum orderStatus;
    private ArrayList<bagItem> selectedProducts; //Declare the ArrayList as a Class field with Product objects

    //Order Constructor
    public Order(int orderID, int customerID, double totalAmount, double totalShipping, String orderDate, String dispatchDate, int shippingID, String orderStatus, ArrayList<bagItem> selectedProducts){
        this.orderID = orderID;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
        this.totalShipping = totalShipping;
        this.orderDate = orderDate;
        this.dispatchDate = dispatchDate;
        this.shippingID = shippingID;
        this.orderStatus = orderStatus;
        this.selectedProducts = selectedProducts; //Assign the provided ArrayList to the class field
    }

    //Create an order with an order number /"orderID" (NOT RANDOM, but in order like, 1, 2, 3,4..)

    //Method to print the details of the order
    public void printDetails(){
        System.out.println("orderID: " + orderID);
        System.out.println("customerID: " + customerID);
        System.out.println("totalAmount: " + totalAmount);
        System.out.println("totalShipping: " + totalShipping);
        System.out.println("orderDate: " + orderDate);
        System.out.println("dispatchDate: " + dispatchDate);
        System.out.println("shippingID: " + shippingID);
        System.out.println("orderStatus: " + orderStatus);
        System.out.println("selectedProducts: " + selectedProducts);
        System.out.println();
    }
}