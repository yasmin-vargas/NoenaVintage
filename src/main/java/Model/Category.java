package Model;

public class Category {
    private int categoryID;
    private String categoryName;
    private String categoryDescription;
    private String parentCategory;
    @Entity
    public Category(int categoryID, String categoryName, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.parentCategory = parentCategory;
    }

    //Setters and Getters
    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
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
}