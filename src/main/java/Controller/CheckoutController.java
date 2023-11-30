package Controller;
import Model.Order;
import Model.OrderStatusEnum;
import Model.StockItem;
import Repository.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.math.BigDecimal;

public class CheckoutController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private List<StockItem> stockItems;
    @Autowired
    private ShoppingBagController shoppingBagController;
    @GetMapping("/checkoutsummary")
    // Get Checkout summary
    public ResponseEntity<Map<String, Object>> getCheckoutSummary() {
        BigDecimal totalAmount = shoppingBagController.getTotalAmount();
        BigDecimal shippingCost = shoppingBagController.getShippingCost();

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
    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0); // Initialize variable "totalAmount" to 0.0
        for (StockItem stockItem : stockItems) {
            BigDecimal itemTotal = stockItem.getStockPrice().multiply(BigDecimal.valueOf(stockItem.getStockQty()));
            totalAmount = totalAmount.add(itemTotal);
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
