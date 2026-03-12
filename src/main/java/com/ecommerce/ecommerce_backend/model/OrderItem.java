package com.ecommerce.ecommerce_backend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private String productId;
    private String name;
    private double price;
    private int qty;
}
