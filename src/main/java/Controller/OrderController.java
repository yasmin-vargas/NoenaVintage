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
import Model.Product;
import Model.OrderStatusEnum;
import Repository.OrderData;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderData orderData;
    @Autowired
    public OrderController(OrderData orderData) {
        this.orderData = orderData;
    }
    @GetMapping("/all-orders")
    public List<Order> getAllOrders() {
        return orderData.findAll();
    }
    @GetMapping("/{orderNumber}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderNumber) {
        Optional<Order> orderOptional = orderData.findById(orderNumber);
        return orderOptional.map(order -> ResponseEntity.ok(order)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{orderId}/selectedProducts")
    public List<Product> getSelectedProductsForOrder(@PathVariable Long orderNumber) {
        return orderData.findSelectedProductsByOrderNumber(orderNumber);
    }
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Order>> getOrderHistoryByUser(@PathVariable Long userID) {
        List<Order> orderHistory = orderData.findByUserID(userID);
        if (orderHistory.isEmpty()) {
            // Handle the case where no orders are found for the user
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderHistory);
    }
    // Method to create new order and add it to the orderList when the customer places an order
    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrderFromBag(@RequestBody User user) {
        ShoppingBag shoppingBag = ShoppingBagData.findByUser(user);
        // Create a new order
        Order order = new Order();
        order.setUser(user);
        // Add products from the shopping bag to the order
        for (Product product : ShoppingBagController.getBagItems()) {
            order.addProduct(product);
        }
        // Set the initial orderstatus to "Confirmed" (cause we are not dealing with payment)
        order.setOrderStatus(OrderStatusEnum.CONFIRMED);
        // Save the order to the database
        orderData.save(order);
        return order;
    }

    @PutMapping("/{orderNumber}")
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
    @PutMapping("/{orderNumber}/status/{newOrderStatus}")
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
    @DeleteMapping("/{orderNumber}")
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

//Maybe create a random number, starting 1000 to create a fake trackingnumber.