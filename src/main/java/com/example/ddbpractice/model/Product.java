package com.example.ddbpractice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class Product {
    
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }
    
    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }
    
    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }
    
    @DynamoDbAttribute("price")
    public BigDecimal getPrice() {
        return price;
    }
    
    @DynamoDbAttribute("category")
    public String getCategory() {
        return category;
    }
    
    @DynamoDbAttribute("stock")
    public Integer getStock() {
        return stock;
    }
    
    @DynamoDbAttribute("createdAt")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    @DynamoDbAttribute("updatedAt")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
} 