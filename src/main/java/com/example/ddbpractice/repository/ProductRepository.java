package com.example.ddbpractice.repository;

import com.example.ddbpractice.model.Product;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    
    private final DynamoDbTable<Product> productTable;
    
    public ProductRepository(DynamoDbEnhancedClient enhancedClient) {
        this.productTable = enhancedClient.table("Product", TableSchema.fromBean(Product.class));
    }
    
    public Product save(Product product) {
        productTable.putItem(product);
        return product;
    }
    
    public Optional<Product> findById(String id) {
        try {
            Product product = productTable.getItem(Key.builder().partitionValue(id).build());
            return Optional.ofNullable(product);
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }
    
    public List<Product> findAll() {
        return productTable.scan(ScanEnhancedRequest.builder().build())
                .items()
                .stream()
                .collect(Collectors.toList());
    }
    
    public Product update(Product product) {
        return save(product); // DynamoDB에서는 putItem이 update 역할도 함
    }
    
    public void deleteById(String id) {
        productTable.deleteItem(Key.builder().partitionValue(id).build());
    }
    
    public void createTableIfNotExists() {
        try {
            productTable.createTable();
        } catch (Exception e) {
            // 테이블이 이미 존재하면 무시
            System.out.println("Table already exists or creation failed: " + e.getMessage());
        }
    }
} 