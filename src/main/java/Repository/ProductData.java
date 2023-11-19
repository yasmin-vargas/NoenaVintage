package Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Product;

//Constructor
public class ProductData {
    public static ArrayList<Product> getProductList(Connection conn) {  //method to get the productList
        ArrayList<Product> productList = new ArrayList<>();} //creates ArrayList productList
    Statement stmt = null;
    String query = "SELECT * FROM Product";  // Executes a SQL query
        try {
        stmt = conn.createStatement();  // Create a SQL statement
        ResultSet rs = stmt.executeQuery(query);
        // Process the results and create Category objects
        while (rs.next()) {
            int productID = rs.getInt("productID");
            String Category = rs.getString("Category");
            String productName = rs.getString("productName");
            String productBrand = rs.getString("productBrand");
            String productDecade = rs.getString("productDecade");
            String productDescription = rs.getString("productDescription");
            double importPrice = rs.getDouble("importPrice");
            double productPrice = rs.getDouble("productPrice");
            double discountPrice = rs.getDouble("discountPrice");
            String productColour = rs.getString("productColour");
            String productSize = rs.getString("productSize");
            String productQuantity = rs.getString("productQuantity");
            String supplierCode = rs.getString("supplierCode");
            System.out.println(productName);

            Product product = new Product(productID, Category, productName, productBrand, productDecade, productDescription, importPrice, productPrice, discountPrice, productColour, productSize, productQuantity, supplierCode);
            productList.add(product);
        }
    } catch (
    SQLException e) {
        throw new Error("Problem", e);
    } finally {
        if (stmt != null) stmt.close();}
}
        return productList;
                }
                }

//Mangler VIEW/ADD/EDIT/DELETE