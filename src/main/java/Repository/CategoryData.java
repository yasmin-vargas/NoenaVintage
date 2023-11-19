package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Category;


//Constructor
public class CategoryData { //Modified code snippet from Class
    public static ArrayList<Category> getCategoryList(Connection conn) {
        ArrayList<Category> categoryList = new ArrayList<>();
        Statement stmt = null;
        String query = "SELECT * FROM Category";  // Executes a SQL query
        try {
            stmt = conn.createStatement();  // Create a SQL statement
            ResultSet rs = stmt.executeQuery(query);
            // Process the results and create Category objects
            while (rs.next()) {
                int categoryID = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                System.out.println(categoryName);
                Category category = new Category(categoryID, categoryName, categoryDescription);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            if (stmt != null) stmt.close();}
    }
        return categoryList;
    }
}
//Mangler VIEW/ADD/EDIT/DELETE