package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;
    private String categoryName;
    private String categoryDescription;
    private String parentCategory;
    private String categoryImageURL;
    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
            joinColumns = @JoinColumn(name = "categoryName"),
            inverseJoinColumns = @JoinColumn(name = "productID"))
    private List<ProductCategory> productCategories;
    public Category() {  // Empty constructor
    }
    public Category(String categoryName, String categoryDescription, String parentCategory, String categoryImageURL) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.parentCategory = parentCategory;
        this.categoryImageURL = categoryImageURL;
    }

    //Setters and Getters
    public long getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    public String getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
    public String getCategoryImageURL() {
        return categoryImageURL;
    }
    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }
}