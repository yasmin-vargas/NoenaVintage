package Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
@Entity
@Table(name="Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageID;
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;

    private boolean isMainImage;

    // Constructors
    public Image() {  // Default constructor
    }
    public Image(String imageURL, Product product, Variant variant, boolean isMainImage) {
        this.imageURL = imageURL;
        this.product = product;
        this.isMainImage = isMainImage;
    }

    // Getters and setters
    public long getImageID() {return imageID;}
    public void setImageID(long imageID) {this.imageID = imageID;}
    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public Variant getVariant() {return variant;}
    public void setVariant(Variant variant) {this.variant = variant;}
    public boolean isMainImage() {return isMainImage;}
    public void setMainImage(boolean mainImage) {isMainImage = mainImage;}
}


