package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Order;
import com.noenavintage.app.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
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