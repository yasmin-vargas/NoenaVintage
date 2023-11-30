package Model;
public enum OrderStatusEnum {  //Creates an enum enumeration that defines all possible orderStatus
    CREATED,
    CONFIRMED,  //Once payment successfull in Payment Integration
    FAILED,  //Payment has failed
    CANCELLED,
    SHIPPED,    //Once shipped, and Shipmondo trackingNumber available
    DELIVERED,
    RETURNED
}



