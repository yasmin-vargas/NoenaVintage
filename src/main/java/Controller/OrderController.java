package Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Model.Order;
import Model.User;
import Model.ShoppingBag;
import Repository.ShoppingBagData;
import Repository.UserData;
import Model.OrderItem;
import Model.OrderStatusEnum;
import Repository.OrderData;
import java.math.BigDecimal;
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
    private ShoppingBag shoppingBag;
    @Autowired
    public OrderController(OrderData orderData) {
        this.orderData = orderData;
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
        List<Order> orderHistory = orderData.findByUserID(userID);
        if (orderHistory.isEmpty()) {
            // Handle the case where no orders are found for the user
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderHistory);
    }
    // Method to create new order and add it to the orderList when the customer places an order
    @PostMapping("/createorder")
    public ResponseEntity<Order> createOrderFromBag(@RequestBody User user) {
        // Use the existing instance variable
        Long userID = user.getUserID();
        ShoppingBag shoppingBag = shoppingBagData.findBagByUserId(userID);
        if (shoppingBag != null && !shoppingBag.getBagItems().isEmpty()) {
            Order order = createOrder(user, shoppingBag);
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
                .map(bagItem -> new OrderItem(order, bagItem))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrderStatus(OrderStatusEnum.CONFIRMED);
        return order;
    }

    @PutMapping("/update{orderNumber}")
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
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderNumber, @PathVariable String newStatus) {
        orderData.updateOrderStatus(orderNumber, newStatus);
        // Check if the new orderStatus is "Confirmed"
        if ("Confirmed".equalsIgnoreCase(newStatus)) {
            // Trigger actions to send order confirmation and tracking number
            orderData.sendOrderConfirmation(orderNumber);
            orderData.sendTrackingNumber(orderNumber);
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

    // Generate a random tracking number starting from 1000
    private int generateTrackingNumber(){
        return new Random().nextInt(9000) + 1000;
    }
}

