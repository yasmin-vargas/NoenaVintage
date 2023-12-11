package Controller;
import Model.Order;
import Model.User;
import Model.OrderItem;
import Model.BagItem;
import Repository.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private ShoppingBagController shoppingBagController;
    @Autowired
    public CheckoutController(OrderData orderData, ShoppingBagController shoppingBagController) {
        this.orderData = orderData;
        this.shoppingBagController = shoppingBagController;
    }

    // Get Checkout summary
    @GetMapping("/checkoutsummary")
    public ResponseEntity<Map<String, Object>> getCheckoutSummary(@RequestParam Long userID) {
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(userID);
        BigDecimal shippingCost = shoppingBagController.getShippingCost();

        // Apply discount if available
        Map<String, Object> discountDetails = applyDiscountCode(userID, Map.of());

        // Convert checkout details to a format suitable for JSON response
        Map<String, Object> checkoutSummary = calculateCheckoutSummary(totalAmount, shippingCost, discountDetails);
        return ResponseEntity.ok(checkoutSummary);

    }

    // Apply discount code
    @PostMapping("/applydiscount")
    public Map<String, Object> applyDiscountCode(@RequestParam Long userID, @RequestBody Map<String, String> requestBody) {
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(userID);

        // Extract discount code from the request body
        String discountCode = requestBody.get("discountCode");

        //  Calculate discount and get updated checkout summary
        Map<String, Object> discountDetails = calculateDiscount(discountCode, totalAmount);

        if (discountDetails.isEmpty()) {
            // Return an error response for an invalid discount code
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid discount code");
        }

        // Return the applied discount in the response
        return discountDetails;
    }

    // Method to calculate the checkout summary
    private Map<String, Object> calculateCheckoutSummary(BigDecimal totalAmount, BigDecimal shippingCost, Map<String, Object> discountDetails) {
        // Get the discount amount
        BigDecimal discountAmount = (BigDecimal) discountDetails.getOrDefault("discountAmount", BigDecimal.ZERO);
        // Calculate the discounted total amount
        BigDecimal discountedTotalAmount = totalAmount.subtract(discountAmount);

        return Map.of(
                "totalAmount", totalAmount,
                "discountedTotalAmount", discountedTotalAmount,
                "discountAmount", discountAmount,
                "shippingCost", shippingCost
        );
    }

    // Method to validate and calculate the discount
    private Map<String, Object> calculateDiscount(String discountCode, BigDecimal totalAmount) {
        BigDecimal discountPercentage = BigDecimal.ZERO;
        if ("NOENA20".equals(discountCode)) {  // If the discount code is "NOENA20", apply a 20% discount
            discountPercentage = new BigDecimal("0.20");
        } else {
            // Invalid discount code
            return Map.of();
        }

            // Calculate discounted total amount
            BigDecimal discountAmount = totalAmount.multiply(discountPercentage);
            BigDecimal discountedAmount = totalAmount.subtract(discountAmount);

            // Construct and return the updated checkout summary
            return Map.of(
                    "totalAmount", discountedAmount,
                    "discountAmount", discountAmount
            );
    }

    // Confirm order, create a new order
    @PostMapping("/confirm")
    public ResponseEntity<Map<String, Object>> confirmCheckout(@RequestBody User user) {
        // Get shopping bag items and total amount
        Long userID = user.getUserID();
        List<BagItem> bagItems = shoppingBagController.getBagItems(userID);
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(userID);
        // Map bagItems to orderItems
        List<OrderItem> orderItems = mapBagItemsToOrderItems(bagItems);

        // Create an Order
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        // Save the order in the database
        Order savedOrder = orderData.save(order);

        // Additional confirmation details
        Map<String, Object> confirmationDetails = Map.of(
                "orderStatus", savedOrder.getOrderStatus(),
                "confirmationMessage", "Thank you for your order!"
        );
        // Clear the shopping bag after creating the order
        shoppingBagController.clearShoppingBag(userID);

        return ResponseEntity.ok(confirmationDetails);
    }

    // Helper method to map BagItems to OrderItems
    private List<OrderItem> mapBagItemsToOrderItems(List<BagItem> bagItems) {
        return bagItems.stream()
                .map(bagItem -> new OrderItem(null, bagItem)) // Assuming OrderItem has a constructor taking an Order and BagItem
                .collect(Collectors.toList());
    }

    // Send order confirmation and tracking number
    @PostMapping("/sendconfirm/{orderNumber}")
    public ResponseEntity<Void> sendOrderConfirmation(@PathVariable Long orderNumber) {
        orderData.sendOrderConfirmation(orderNumber);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/sendtracking/{orderNumber}")
    public ResponseEntity<Void> sendTrackingNumber(@PathVariable Long orderNumber) {
        orderData.sendTrackingNumber(orderNumber);
        return ResponseEntity.ok().build();
    }
}
