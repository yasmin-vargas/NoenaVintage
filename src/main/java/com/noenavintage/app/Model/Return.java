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
    @ManyToOne
    @JoinColumn(name = "orderNumber")
    private Order returnfromOrder;
    private Timestamp returnDate;
    private String returnReason;
    public Return() {  // Default constructor required by JPA
    }
    public Return(long returnID, Order returnfromOrder, Timestamp returnDate, String returnReason) {
        this.returnID = returnID;
        this.returnfromOrder = returnfromOrder;
        this.returnDate = returnDate;
        this.returnReason = returnReason;
    }

    // Getters and setters
    public long getReturnID() { return returnID;}
    public void setReturnID(long returnID) { this.returnID = returnID;}
    public Order getReturnfromOrder() { return returnfromOrder;}
    public void setReturnfromOrder(Order returnfromOrder) { this.returnfromOrder = returnfromOrder;}
    public Timestamp getReturnDate() { return returnDate;}
    public void setReturnDate(Timestamp returnDate) { this.returnDate = returnDate;}
    public String getReturnReason() { return returnReason;}
    public void setReturnReason(String returnReason) { this.returnReason = returnReason;}
}
