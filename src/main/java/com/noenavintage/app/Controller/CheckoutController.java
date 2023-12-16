package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Order;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Model.OrderItem;
import com.noenavintage.app.Model.BagItem;
import com.noenavintage.app.Repository.OrderData;
import java.util.Map;
import java.util.List;
import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private ShoppingBagController shoppingBagController;
    @Autowired
    public CheckoutController() {
    }

    // Get Checkout summary
    @GetMapping("/checkoutSummary")
    public ResponseEntity<Map<String, Object>> getCheckoutSummary(@RequestParam User user) {
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(user);
        BigDecimal shippingCost = shoppingBagController.getShippingCost();

        // Apply discount if available
        Map<String, Object> discountDetails = applyDiscountCode(user, Map.of());

        // Convert checkout details to a format suitable for JSON response
        Map<String, Object> checkoutSummary = calculateCheckoutSummary(totalAmount, shippingCost, discountDetails);
        return ResponseEntity.ok(checkoutSummary);

    }

    // Apply discount code
    @PostMapping("/applyDiscountCode")
    public Map<String, Object> applyDiscountCode(@RequestParam User user, @RequestBody Map<String, String> requestBody) {
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(user);

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
    @PostMapping("/confirmCheckout")
    public ResponseEntity<Map<String, Object>> confirmCheckout(@RequestBody User user) {
        // Get shopping bag items and total amount
        Long userID = user.getUserID();
        List<BagItem> bagItems = shoppingBagController.getBagItems(user);
        BigDecimal totalAmount = shoppingBagController.getTotalAmount(user);
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
        shoppingBagController.clearShoppingBag(user);

        return ResponseEntity.ok(confirmationDetails);
    }

    // Helper method to map BagItems to OrderItems
    private List<OrderItem> mapBagItemsToOrderItems(List<BagItem> bagItems) {
        return bagItems.stream()
                .map(bagItem -> new OrderItem(new Order(), bagItem, bagItem.getBagItemQty()))
                .collect(Collectors.toList());
    }

    // Send order confirmation and tracking number
    @PostMapping("/sendConfirmation/{orderNumber}")
    public ResponseEntity<Void> sendOrderConfirmation(@PathVariable Long orderNumber) {
        orderData.sendOrderConfirmation(orderNumber);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/sendTrackingNumber/{orderNumber}")
    public ResponseEntity<Void> sendTrackingNumber(@PathVariable Long orderNumber) {
        String trackingNumber = generateTrackingNumber();  // Generate a tracking number
        orderData.sendTrackingNumber(orderNumber, trackingNumber);
        return ResponseEntity.ok().build();
    }

    // Generate a random tracking number as a string
    public String generateTrackingNumber() {
        return "TRK" + (new Random().nextInt(9000) + 1000);
    }
}
