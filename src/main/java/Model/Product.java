package Model;
import java.util.List;
public class Product{
    private int productID;
    private String Category;
    private String productName;
    private String productBrand;
    private String productDecade;
    private String productDescription;
    private double importPrice;
    private double productPrice;
    private double discountPrice;
    private List<String> productColour; //list of availabe colours
    private List<String> productSize; //List of available sizes
    private int productQuantity;  //relation to stockQuantity (sum of all stockQuantity of one Product)
    private String supplierCode;

    // Product Constructor
    public Product(int productID, String Category, String productName, String productBrand, String productDecade, String productDescription, double importPrice, double productPrice, double discountPrice,List<String> productColour,List<String> productSize, int productQuantity, String supplierCode){
        this.productID = productID;
        this.Category = Category;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDecade = productDecade;
        this.productDescription = productDescription;
        this.importPrice = importPrice;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.productColour = productColour;
        this.productSize = productSize;
        this.productQuantity = productQuantity;
        this.supplierCode = supplierCode;
    }

    //Getters and setters
    public int getProductID(){

        return productID;
    }
    public void setProductID(int productID) {

        this.productID = productID;
    }

    public String getCategory(){

        return Category;
    }
    public void setCategory(String Category) {

        this.Category = Category;
    }

    public String getProductName(){

        return productName;
    }
    public void setProductName(String productName) {

        this.productName = productName;
    }
    public String getProductBrand(){
        return
                productBrand;
    }
    public void setProductBrand(String productBrand) {

        this.productBrand = productBrand;
    }

    public String getProductDecade(){
        return
                productDecade;
    }
    public void setProductDecade(String productDecade) {

        this.productDecade = productDecade;
    }
    public String getProductColour(){
        return
                productColour;
    }
    public void setProductColour(String productColour) {

        this.productColour = productColour;
    }
    public String getProductDescription(){
        return
                productDescription;
    }
    public void setProductDescription(String productDescription) {

        this.productDescription = productDescription;
    }

    public double getImportPrice(){
        return importPrice;
    }
    public void setImportPrice(double importPrice) {

        this.importPrice = importPrice;
    }

    public double getProductPrice(){

        return productPrice;
    }
    public void setProductPrice(double productPrice) {

        this.productPrice = productPrice;
    }
    public double getDiscountPrice(){

        return discountPrice;
    }
    public void setDiscountPrice(double discountPrice) {

        this.discountPrice = discountPrice;
    }
    public String getProductSize(){

        return productSize;
    }
    public void setProductSize(String productSize) {

        this.productSize = productSize;
    }
    public int getProductQuantity(){

        return productQuantity;
    }
    public void setProductQuantity(int stockQuantity) {

        this.productQuantity = stockQuantity;
    }
    public String getSupplierCode(){

        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {

        this.supplierCode = supplierCode;
    }
}
