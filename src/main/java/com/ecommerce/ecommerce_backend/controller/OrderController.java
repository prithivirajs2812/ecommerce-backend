package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Order;
import com.ecommerce.ecommerce_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** Place an order — any authenticated user */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, Authentication auth) {
        return ResponseEntity.ok(orderService.placeOrder(order, auth.getName()));
    }

    /** Get current user's orders */
    @GetMapping("/my")
    public ResponseEntity<List<Order>> myOrders(Authentication auth) {
        return ResponseEntity.ok(orderService.getOrdersByUser(auth.getName()));
    }

    /** Get ALL orders — SELLER only (protected in SecurityConfig) */
    @GetMapping("/all")
    public ResponseEntity<List<Order>> allOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /** Update order status — SELLER only (protected in SecurityConfig) */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        if (newStatus == null || newStatus.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "status is required"));
        }
        try {
            return ResponseEntity.ok(orderService.updateOrderStatus(id, newStatus));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
