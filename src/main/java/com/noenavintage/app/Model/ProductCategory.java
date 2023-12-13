package com.noenavintage.app.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryID;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    public ProductCategory() {
    }
    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    // Getters and setters
    public Long getProductCategoryID() {return productCategoryID;}
    public void setProductCategoryID(Long productCategoryID) {this.productCategoryID = productCategoryID;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}
}


