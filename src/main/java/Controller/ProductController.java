package Controller;
import Model.Product;
import Repository.ProductData;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductData productData;

    public ProductController(Connection conn) {
        this.productData = new ProductData(conn);
    }
    public List<Product> getProductsSortedByPrice(boolean ascending) {
        String order = ascending ? "ASC" : "DESC";
        String sql = "SELECT * FROM Product ORDER BY price " + order;

        public List<Product> getProductsSortedByNewest(boolean descending) {
            String sql = "SELECT * FROM Product ORDER BY productDate DESC";

            public static void main(String[] args){
                // Get products sorted by price in ascending order
                List<Product> productsAscending = controller.getProductsSortedByPrice(true);
                System.out.println("Products sorted by price (ascending):");
                for (Product product : productsAscending) {
                    System.out.println(product);
                }
                // Get products sorted by price in descending order
                List<Product> productsDescending = controller.getProductsSortedByPrice(false);
                System.out.println("Products sorted by price (descending):");
                for (Product product : productsDescending) {
                    System.out.println(product);
                }
                // Get products sorted by newest date (descending order)
                List<Product> productsNewest = controller.getProductsSortedByNewest();
                System.out.println("Products sorted by newest:");
                for (Product product : productsNewest) {
                    System.out.println(product);
                }
            }
        }
    }
}

//Mangler Search by "".

