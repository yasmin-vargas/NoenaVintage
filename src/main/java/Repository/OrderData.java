package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.BagItem;
import Model.Order;
import Model.OrderStatusEnum;

//Constructor
public class OrderData {
    public static ArrayList<Order> getOrderList(Connection conn) {
        ArrayList<Order> orderList = new ArrayList<>();
        Statement stmt = null;
        String query = "SELECT * FROM CustomerOrder";  // Executes a SQL query
        try {
            stmt = conn.createStatement();  // Create a SQL statement
            ResultSet rs = stmt.executeQuery(query);
            // Process the results and create Category objects
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customerID = rs.getInt("customerID");
                double totalAmount = rs.getDouble("totalAmount");
                double totalShipping = rs.getDouble("totalShipping");
                String orderDate = rs.getString("orderDate");
                String dispatchDate = rs.getString("dispatchDate");
                int shippingID = rs.getInt("shippingID");
                OrderStatusEnum orderStatus = rs.getOrderStatus("orderStatus");
                ArrayList<BagItem> selectedProducts = getSelectedProducts(orderID); //Define method to return the ArrayList<String> of selected products for each Order
                System.out.println(orderID);
                Order order = new Order(orderID, customerID, totalAmount, totalShipping, orderDate, dispatchDate,shippingID, orderStatus, selectedProducts);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            if (stmt != null) stmt.close();}
    }
        return orderList;
}

    // Define method to get selectedProducts for a specific order
    private ArrayList<BagItem> getSelectedProducts(int orderID){
        ArrayList<BagItem> selectedProducts = new ArrayList<>();
        // make SQL queries for database
        return selectedProducts;
    }
}
//Mangler VIEW/ADD/EDIT/DELETE