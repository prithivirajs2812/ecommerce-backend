package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> body) {
        try {
            Number amountNumber = (Number) body.get("amount");
            if (amountNumber == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "amount is required"));
            }

            double amount = amountNumber.doubleValue();

            // Assuming INR for Razorpay
            String orderId = paymentService.createOrder(amount, "INR");
            return ResponseEntity.ok(Map.of("orderId", orderId));

        } catch (RazorpayException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
}
