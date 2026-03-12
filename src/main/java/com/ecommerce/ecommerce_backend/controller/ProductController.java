package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /api/products?search=headphones&category=Electronics
    @GetMapping("/products")
    public List<Product> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category) {
        return service.getAllProducts(search, category);
    }

    // GET /api/products/{id}
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.getProductById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/products
    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // PUT /api/products/{id}
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(service.updateProduct(id, product));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // GET /api/categories
    @GetMapping("/categories")
    public List<String> getCategories() {
        return service.getCategories();
    }
}