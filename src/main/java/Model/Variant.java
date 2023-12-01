package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;

@Entity
@Table(name="Variant")
public class Variant extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long variantID;
    private String variantName;
    private String variantColour;
    private String variantSize;
    private BigDecimal variantPrice;
    private int stockQty;
    @OneToOne
    @JoinColumn(name = "main_image_id")
    private Image variantImage;   //Main image
    @OneToMany(mappedBy = "variant")
    private Set<Image> variantImages = new HashSet<>(); //Gallery
    public Variant() {  // Default constructor necessary for JPA entities
    }

    public Variant(long variantID, String variantName, BigDecimal variantPrice, String variantColour, String variantSize, int stockQty) {
        this.variantID = variantID;
        this.variantName = variantName;
        this.variantColour = variantColour;
        this.variantSize = variantSize;
        this.variantPrice = variantPrice;
        this.stockQty = stockQty;
    }

    // Getters and setters
    public long getVariantID() { return variantID; }
    public void setVariantID(long variantID) { this.variantID = variantID; }
    public String getVariantName() { return variantName; }
    public void setVariantName(String variantName) { this.variantName = variantName; }
    public BigDecimal getVariantPrice() { return variantPrice; }
    public void setVariantPrice(BigDecimal variantPrice) { this.variantPrice = variantPrice; }
    public String getVariantColour() { return variantColour; }
    public void setVariantColour(String variantColour) { this.variantColour = variantColour; }
    public String getVariantSize() { return variantSize; }
    public void setVariantSize(String variantSize) { this.variantSize = variantSize; }
}
