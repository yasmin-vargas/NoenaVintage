package Model;
public enum OrderStatusEnum {  //Creates an enum enumeration that defines all possible orderStatus
    CREATED,
    NEW,
    DISPATCHED,
    FAILED,
    CANCELLED,
    DELIVERED,
    RETURNED
}
@Entity
public class OrderStatus {
    private OrderStatusEnum orderStatus;  //An instance of the "OrderStatusEnum" enumeration
    private int statusID;           // Unique identifier of each order status record
    private String orderDate;

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatusEnum orderStatus){
        this.orderStatus = orderStatus;
    }
    public int getStatusID() {
        return statusID;}
    public void setStatusID(int statusID){
        this.statusID = statusID;
    }
    public String getOrderDate() {
        return orderDate; }
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;}
}


//Maybe create a random number, starting 1000 to create a fake trackingnumber.
