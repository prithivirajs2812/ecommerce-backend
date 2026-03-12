package com.ecommerce.ecommerce_backend.config;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the MongoDB database with sample products on first startup.
 * Only inserts if the products collection is empty.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    Product.builder().name("Premium Wireless Headphones")
                            .description("Crystal clear sound with active noise cancellation and 30-hour battery life")
                            .price(129.99).category("Electronics")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400&h=280&fit=crop")
                            .rating(4.5).reviewCount(248).stock(50).build(),
                    Product.builder().name("Minimalist Watch")
                            .description("Elegant stainless steel watch with sapphire crystal glass and Swiss movement")
                            .price(249.99).category("Accessories")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400&h=280&fit=crop")
                            .rating(4.8).reviewCount(126).stock(30).build(),
                    Product.builder().name("Running Shoes Pro")
                            .description("Ultra-lightweight foam sole for peak running performance and comfort")
                            .price(89.99).category("Footwear")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1491553895911-0055eca6402d?w=400&h=280&fit=crop")
                            .rating(4.2).reviewCount(334).stock(100).build(),
                    Product.builder().name("Skincare Glow Set")
                            .description("Complete daily routine with vitamin C serum, moisturizer, and SPF protection")
                            .price(59.99).category("Beauty")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1585386959984-a4155224a1ad?w=400&h=280&fit=crop")
                            .rating(4.6).reviewCount(92).stock(75).build(),
                    Product.builder().name("Polaroid Camera")
                            .description("Capture vibrant instant photos with double exposure mode and built-in flash")
                            .price(74.99).category("Electronics")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=400&h=280&fit=crop")
                            .rating(4.3).reviewCount(181).stock(40).build(),
                    Product.builder().name("Leather Backpack").description(
                            "Handcrafted full-grain Italian leather with padded laptop sleeve and brass hardware")
                            .price(199.99).category("Bags")
                            .imageUrl("https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400&h=280&fit=crop")
                            .rating(4.7).reviewCount(67).stock(25).build(),
                    Product.builder().name("Smart Fitness Band")
                            .description("Track heart rate, sleep, steps, and workouts with 7-day battery life")
                            .price(49.99).category("Electronics")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1575311373937-040b8e1fd5b6?w=400&h=280&fit=crop")
                            .rating(4.1).reviewCount(412).stock(80).build(),
                    Product.builder().name("Scented Candle Set")
                            .description(
                                    "Three signature fragrances in hand-poured soy wax candles for a cozy ambiance")
                            .price(34.99).category("Home")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1602523961358-f9f03dd557db?w=400&h=280&fit=crop")
                            .rating(4.9).reviewCount(55).stock(60).build(),
                    Product.builder().name("Sunglasses Elite")
                            .description("Polarized UV400 lenses in a lightweight titanium frame for everyday style")
                            .price(119.99).category("Accessories")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1473496169904-658ba7574b0d?w=400&h=280&fit=crop")
                            .rating(4.4).reviewCount(203).stock(45).build(),
                    Product.builder().name("Yoga Mat Premium")
                            .description("6mm natural rubber mat with alignment lines and non-slip surface")
                            .price(44.99).category("Sports")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1506629082955-511b1aa562c8?w=400&h=280&fit=crop")
                            .rating(4.6).reviewCount(289).stock(90).build(),
                    Product.builder().name("Stainless Water Bottle")
                            .description("24-hour cold, 12-hour hot insulation with leak-proof lid — 750ml")
                            .price(29.99).category("Sports")
                            .imageUrl(
                                    "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=400&h=280&fit=crop")
                            .rating(4.5).reviewCount(550).stock(120).build(),
                    Product.builder().name("Desk Plant Trio")
                            .description(
                                    "Low-maintenance succulents in minimalist white ceramic pots for your workspace")
                            .price(39.99).category("Home")
                            .imageUrl("https://images.unsplash.com/photo-1545165375-a0c78e5a83bf?w=400&h=280&fit=crop")
                            .rating(4.8).reviewCount(38).stock(35).build());
            productRepository.saveAll(products);
            System.out.println("✅ DataSeeder: Inserted " + products.size() + " sample products into MongoDB.");
        } else {
            System.out.println("ℹ️  DataSeeder: Products already exist, skipping seed.");
        }
    }
}
