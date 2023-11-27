package Controller;
import Model.BagItem;
import Model.Order;
import Model.OrderStatusEnum;
import Repository.BagItemData;
import Repository.OrderData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;
import java.util.List;

public class CheckoutController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private List<BagItem> bagItems;
    @Autowired
    private ShoppingBagController shoppingBagController;
    @GetMapping("/checkoutsummary")
    // Get Checkout summary
    public ResponseEntity<Map<String, Object>> getCheckoutSummary() {
        double totalAmount = shoppingBagController.getTotalAmount();
        double shippingCost = shoppingBagController.getShippingCost();

        // Convert checkout details to a format suitable for JSON response
        // Using Map.of to create an unmodifiable map
        Map<String, Object> checkoutSummary = Map.of(
                "totalAmount", totalAmount,
                "shippingCost", shippingCost
        );
        return ResponseEntity.ok(checkoutSummary);
    }

    // Method to calculate getTotalAmount() bagItemPrice * bagItemQuantity
    @GetMapping("/total-amount")
    public double getTotalAmount() {
        double totalAmount = 0.0; // Initialize variable "totalAmount" to 0.0
        for (BagItem bagItem : bagItems) {
            totalAmount += bagItem.getBagItemPrice()* bagItem.getBagItemQty();
        }
        return totalAmount;
    }
    // Apply discount code
    @PostMapping("/apply-discount")
    public ResponseEntity<Void> applyDiscountCode(@RequestBody Map<String, String> discountInfo) {
        return ResponseEntity.ok().build();
    }
    // Confirm order
    @PostMapping("/confirm/{orderNumber}")
    public ResponseEntity<Map<String, Object>> confirmCheckout(@PathVariable Long orderId) {
        Optional<Order> orderOptional = orderData.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();

            // Update orderStatus to "CONFIRMED"
            order.setOrderStatus(OrderStatusEnum.CONFIRMED);

            // Save the updated order
            orderData.save(order);

            // Creates a map with confirmation details
            Map<String, Object> confirmationDetails = Map.of(
                    "orderStatus", order.getOrderStatus(),
                    "confirmationMessage", "Thank you for your order! Your order is now confirmed."
            );
            return ResponseEntity.ok(confirmationDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Send order confirmation and tracking number
    @PostMapping("/{id}/sendconfirm")
    public ResponseEntity<Void> sendOrderConfirmation(@PathVariable Long orderNumber) {
        orderData.sendOrderConfirmation(orderNumber);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/sendtracking")
    public ResponseEntity<Void> sendTrackingNumber(@PathVariable Long orderNumber) {
        orderData.sendTrackingNumber(orderNumber);
        return ResponseEntity.ok().build();
    }
}
