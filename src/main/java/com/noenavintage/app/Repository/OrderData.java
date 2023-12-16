package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Order;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.OrderItem;
import com.noenavintage.app.Model.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface OrderData extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
    List<Order> findByOrderStatus(OrderStatusEnum orderStatus);
    List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber);
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = 'CONFIRMED' WHERE o.orderNumber = :orderNumber")
    void sendOrderConfirmation(Long orderNumber);
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.trackingNumber = :trackingNumber WHERE o.orderNumber = :orderNumber")
    void sendTrackingNumber(@Param("orderNumber") Long orderNumber, @Param("trackingNumber") String trackingNumber);
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = :newOrderStatus WHERE o.orderNumber = :orderNumber")
    void updateOrderStatus(Long orderNumber, String newOrderStatus);
}