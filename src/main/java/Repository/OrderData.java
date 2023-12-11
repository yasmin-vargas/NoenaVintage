package Repository;
import Model.Order;
import Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderData extends JpaRepository<Order, Long> {
    List<Order> findByUserID(Long userID);
    List<Order> findByOrderStatus(String orderStatus);  // Find orders by status

    // Method to get selected products for a specific order
    List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber);
    void addNewOrder(Order order);

    void sendOrderConfirmation(Long orderNumber);

    void sendTrackingNumber(Long orderNumber);

    void updateOrderStatus(Long orderNumber, String newOrderStatus);
}