package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="Return")
public class Return {
    @Id
    private long returnId;

    @ManyToOne
    @JoinColumn(name = "orderNumber")
    private Order returnfromOrder;
    private Timestamp returnDate;
    private String returnReason;

    public Return() {
        // Default constructor required by JPA
    }

    public Return(long returnId, Order returnfromOrder, Timestamp returnDate, String returnReason) {
        this.returnId = returnId;
        this.returnfromOrder = returnfromOrder;
        this.returnDate = returnDate;
        this.returnReason = returnReason;
        // Initialize other return-specific attributes as needed
    }

    // Getters and setters
    public long getReturnId() { return returnId; }
    public void setReturnId(long returnId) { this.returnId = returnId; }
    public Order getReturnfromOrder() { return returnfromOrder; }
    public void setReturnfromOrder(Order returnfromOrder) { this.returnfromOrder = returnfromOrder; }
    public Timestamp getReturnDate() { return returnDate; }
    public void setReturnDate(Timestamp returnDate) { this.returnDate = returnDate; }
    public String getReturnReason() { return returnReason; }
    public void setReturnReason(String returnReason) { this.returnReason = returnReason; }
}
