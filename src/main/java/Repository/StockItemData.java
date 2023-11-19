package Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.StockItem;

//Constructor
public class StockItemData {
    public static ArrayList<StockItem> getStockItemList(Connection conn) {  //method to get the productList
        ArrayList<StockItem> stockItemList = new stockItemList<>();} //creates ArrayList productList
    Statement stmt = null;
    String query = "SELECT * FROM StockItem";  // Executes a SQL query
        try {
        stmt = conn.createStatement();  // Create a SQL statement
        ResultSet rs = stmt.executeQuery(query);
        // Process the results and create Category objects
        while (rs.next()) {
            int stockID = rs.getInt("stockID");
            double stockPrice = rs.getDouble("stockPrice");
            String stockColour = rs.getString("stockColour");
            String stockSize = rs.getString("stockSize");
            String stockQuantity = rs.getString("stockQuantity");
            System.out.println(stockID);

            StockItem stockItem = new StockItem(stockID, stockPrice, stockColour, stockSize, stockQuantity);
            stockItemList.add(stockItem);
        }
    } catch (
    SQLException e) {
        throw new Error("Problem", e);
    } finally {
        if (stmt != null) stmt.close();}
}
        return stockItemList;
                }
                }

//Mangler VIEW/ADD/EDIT/DELETE