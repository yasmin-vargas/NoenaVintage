package Model;
import Repository.OrderData;
import java.util.ArrayList;
@Entity
public class CustomerAccount extends User {
    private int customerID;
    private String firstName;
    private String lastName;
    private String billing_address;
    private int b_postalCode;
    private String b_city;
    private String shipping_address;
    private String s_postalCode;
    private String s_city;
    private int phone;
    private String email;

    public CustomerAccount(int customerID, String firstName, String lastName, String billing_address, int b_postalCode, String b_city, String shipping_address, int s_postalCode, String s_city, int phone, String email){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.billing_address = billing_address;
        this.b_postalCode = b_postalCode;
        this.b_city = b_city;
        this.shipping_address = shipping_address;
        this.s_postalCode = s_postalCode;
        this.s_city = s_city;
        this.phone = phone;
        this.email = email;
    }

    //Setters and Getters
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String categoryName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBilling_address() {
        return billing_address;
    }
    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }
    public int getB_PostalCode() {
        return b_postalCode;
    }
    public void setB_PostalCode(int b_postalCode) {
        this.b_postalCode = b_postalCode;}
    public String getB_City() {return b_city;
    }
    public void setB_City(String b_city) {this.b_city = b_city;
    }
    public String getShipping_address() {return shipping_address;
    }
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
    public int getS_PostalCode() {return s_postalCode;
    }
    public void setS_PostalCode(int s_postalCode) {this.s_postalCode = s_postalCode;
    }
    public String getS_City() {return s_city;
    }
    public void setS_City(String s_city) {this.s_city = s_city;
    }
    public int getPhone() {return phone;
    }
    public void setPhone(int phone) {this.phone = phone;
    }
    public String getEmail() {return email;
    }
    public void setEmail(String email) {this.email = email;
    }
}
    public void viewOrderHistory() {
        OrderData orderData = new OrderData(); // Create an instance of OrderData
        ArrayList<Order> orderList = orderData.getOrderList(); // Get the orderlist from OrderData
        ArrayList<Order> MyOrders = new ArrayList<>();  //creates new ArrayList MyOrders

        // Iterate through all orders from orderList and filter by customerID
        for (Order order : orderList) {
            if (order.getCustomerID() == this.customerID) {
                MyOrders.add(order);
            }
        }
        // Display the order history
        for (Order order : MyOrders) {
            order.printDetails(); // Method to print order details
        }
    }
}
