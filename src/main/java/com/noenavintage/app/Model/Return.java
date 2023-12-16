package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="Return")
public class Return {
    @Id
    private Long returnID;
    private Timestamp returnDate;
    private String returnReason;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "orderNumber")
    private Order returnfromOrder;
    @Enumerated(EnumType.STRING)
    private ReturnStatusEnum returnStatus;
    public Return() {  // Default constructor required by JPA
    }
    public Return(long returnID, Timestamp returnDate, String returnReason, User user, Order returnfromOrder, ReturnStatusEnum returnStatus) {
        this.returnID = returnID;
        this.returnDate = returnDate;
        this.returnReason = returnReason;
        this.user = user;
        this.returnfromOrder = returnfromOrder;
        this.returnStatus = returnStatus;
    }

    // Getters and setters
    public Long getReturnID() { return returnID;}
    public void setReturnID(Long returnID) { this.returnID = returnID;}
    public Timestamp getReturnDate() { return returnDate;}
    public void setReturnDate(Timestamp returnDate) { this.returnDate = returnDate;}
    public String getReturnReason() { return returnReason;}
    public void setReturnReason(String returnReason) { this.returnReason = returnReason;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Order getReturnfromOrder() { return returnfromOrder;}
    public void setReturnfromOrder(Order returnfromOrder) { this.returnfromOrder = returnfromOrder;}
    public ReturnStatusEnum getReturnStatus() { return returnStatus;}
    public void setReturnStatus(ReturnStatusEnum returnStatus) { this.returnStatus = returnStatus;}
}
