package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="Attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeID;
    private String attributeType;
    private String attributeValue;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "variantID")
    private Variant variant;
    public Attribute() {  // No-argument constructor
    }

    public Attribute(String attributeType, String attributeValue, Product product, Variant variant) {
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
        this.product = product;
        this.variant = variant;
    }

    // Getters and setters
    public Long getAttributeID() {return attributeID;}
    public void setAttributeID(Long attributeID) {this.attributeID = attributeID;}
    public String getAttributeType() {return attributeType;}
    public void setAttributeType(String attributeType) {this.attributeType = attributeType;}
    public String getAttributeValue() {return attributeValue;}
    public void setAttributeValue(String attributeValue) {this.attributeValue = attributeValue;}
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public Variant getVariant() {return variant;}
    public void setVariant(Variant variant) {this.variant = variant;}
}