package Model;
import Model.StockItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="Gallery")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long galleryID;
    String galleryURL;
    long productID;

    // Constructor
    public Gallery(long galleryID, String galleryURL, long productID) {
        this.galleryID = galleryID;
        this.galleryURL = galleryURL;
        this.productID = productID;
    }
    // Getters and setters
    public long getGalleryID() {return galleryID;}
    public void setGalleryID(long galleryID) {this.galleryID = galleryID;}
    public String getGalleryURL() {return galleryURL;}
    public void setGalleryURL(String galleryURL) {this.galleryURL = galleryURL;}
    public long getProductID() {return productID;}
    public void setProductID(long productID) {this.productID = productID;}
}
