package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attributeID;
    private String attributeType;
    private String attributeValue;
    private long productID;
    public Attribute() {  // No-argument constructor
    }

    // Product Constructor
    public Attribute(long attributeID, String attributeType, String attributeValue, long productID) {
        this.attributeID = attributeID;
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
        this.productID = productID;
    }

    // Getters and setters
    public long getAttributeID() {return attributeID;}
    public void setAttributeID(long attributeID) {this.attributeID = attributeID;}
    public String getAttributeType() {return attributeType;}
    public void setAttributeType(String attributeType) {this.attributeType = attributeType;}
    public String getAttributeValue() {return attributeValue;}
    public void setAttributeValue(String attributeValue) {this.attributeValue = attributeValue;}
    public long getProductID() {return productID;}
    public void setProductID(long productID) {this.productID = productID;}
}