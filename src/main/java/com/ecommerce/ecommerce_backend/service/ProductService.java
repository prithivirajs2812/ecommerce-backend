package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    // READ ALL (with optional search/category)
    public List<Product> getAllProducts(String search, String category) {
        List<Product> products;

        if (StringUtils.hasText(search)) {
            products = repo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search, search);
        } else if (StringUtils.hasText(category)) {
            products = repo.findByCategoryIgnoreCase(category);
        } else {
            products = repo.findAll();
        }
        return products;
    }

    // READ BY ID
    public Product getProductById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // UPDATE
    public Product updateProduct(String id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setRating(updatedProduct.getRating());
        existing.setReviewCount(updatedProduct.getReviewCount());
        existing.setStock(updatedProduct.getStock());
        return repo.save(existing);
    }

    // DELETE
    public void deleteProduct(String id) {
        repo.deleteById(id);
    }

    // DISTINCT CATEGORIES
    public List<String> getCategories() {
        return repo.findAll().stream()
                .map(Product::getCategory)
                .filter(c -> c != null && !c.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}