package com.example.ddbpractice.service;

import com.example.ddbpractice.model.Product;
import com.example.ddbpractice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public Product createProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }
    
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product updateProduct(String id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            product.setCreatedAt(existingProduct.get().getCreatedAt());
            product.setUpdatedAt(LocalDateTime.now());
            return productRepository.update(product);
        }
        throw new RuntimeException("Product not found with id: " + id);
    }
    
    public void deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
    
    public void initializeTable() {
        productRepository.createTableIfNotExists();
    }
} 