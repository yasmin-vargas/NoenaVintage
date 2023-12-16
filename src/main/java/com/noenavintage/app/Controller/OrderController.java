package com.noenavintage.app.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.noenavintage.app.Model.Order;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.ShoppingBag;
import com.noenavintage.app.Repository.ShoppingBagData;
import com.noenavintage.app.Repository.UserData;
import com.noenavintage.app.Model.OrderItem;
import com.noenavintage.app.Model.OrderStatusEnum;
import com.noenavintage.app.Repository.OrderData;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private ShoppingBagData shoppingBagData;
    @Autowired
    private UserData userData;
    @Autowired
    private CheckoutController checkoutController;
    @Autowired
    public OrderController() {
    }
    @GetMapping("/allorders")
    public List<Order> getAllOrders() {
        return orderData.findAll();
    }
    @GetMapping("/{orderNumber}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderNumber) {
        Optional<Order> orderOptional = orderData.findById(orderNumber);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{orderNumber}/orderItems")
    public List<OrderItem> getOrderItems(@PathVariable Long orderNumber) {
        return orderData.findOrderItemsByOrderNumber(orderNumber);
    }
    @GetMapping("/history/{userID}")
    public ResponseEntity<List<Order>> getOrderHistoryByUser(@PathVariable Long userID) {
        // Assuming you have a method to retrieve a User by ID in your user data repository
        User user = userData.findByUserID(userID).orElse(null);
        if (user == null) {  // Handle the case where no user is found
            return ResponseEntity.notFound().build();
        }
        List<Order> orderHistory = orderData.findOrderByUser(user);
        if (orderHistory.isEmpty()) {  // Handle the case where no orders are found for the user
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderHistory);
    }
    // Method to create new order and add it to the orderList when the customer places an order
    @PostMapping("/createOrderFromBag")
    public ResponseEntity<Order> createOrderFromBag(@RequestBody User user) {
        // Use the existing instance variable
        Long userID = user.getUserID();
        ShoppingBag shoppingBag = shoppingBagData.findBagByUser(user);
        if (shoppingBag != null && !shoppingBag.getBagItems().isEmpty()) {
            Order order = createOrder(user, shoppingBag);
            List<OrderItem> orderItems = shoppingBag.getBagItems().stream()
                    .map(bagItem -> new OrderItem(order, bagItem, bagItem.getBagItemQty()))
                    .collect(Collectors.toList());
            order.setOrderItems(orderItems);
            Order savedOrder = orderData.save(order);
            // Clear the shopping bag after creating the order
            shoppingBag.getBagItems().clear();
            shoppingBagData.save(shoppingBag);
            return ResponseEntity.ok(savedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Helper method to create order
    private Order createOrder(User user, ShoppingBag shoppingBag) {
        Order order = new Order();
        order.setUser(user);
        // Convert BagItems to OrderItems
        List<OrderItem> orderItems = shoppingBag.getBagItems().stream()
                .map(bagItem -> new OrderItem(order, bagItem, bagItem.getBagItemQty()))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrderStatus(OrderStatusEnum.CONFIRMED);
        return order;
    }

    @PutMapping("/update/{orderNumber}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderNumber, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderData.findById(orderNumber);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            updatedOrder.setOrderNumber(orderNumber);
            Order updated = orderData.save(updatedOrder);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //Managing orderStatus
    @PutMapping("/{orderNumber}/updatestatus/{newOrderStatus}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderNumber, @PathVariable String newOrderStatus) {
        orderData.updateOrderStatus(orderNumber, newOrderStatus);
        // Check if the new orderStatus is "Confirmed"
        if ("CONFIRMED".equalsIgnoreCase(newOrderStatus)) {
            // Generate a tracking number
            String trackingNumber = checkoutController.generateTrackingNumber();
            // Trigger actions to send order confirmation and tracking number
            orderData.sendOrderConfirmation(orderNumber);
            orderData.sendTrackingNumber(orderNumber, trackingNumber);
        }
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{orderNumber}/delete")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderNumber) {
        Optional<Order> existingOrderOptional = orderData.findById(orderNumber);
        if (existingOrderOptional.isPresent()) {
            orderData.deleteById(orderNumber);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

