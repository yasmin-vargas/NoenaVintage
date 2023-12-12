package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="ProductImage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageID;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "imageID")
    private Image image;

    public ProductImage() {
    }

    public ProductImage(Product product, Image image) {
        this.product = product;
        this.image = image;
    }

    // Getters and setters
    public Long getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(Long productImageID) {
        this.productImageID = productImageID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
