package com.project.code.Model;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Document(collection = "reviews")
public class Review {

    @Id 
    private String id;

    @NotNull(message = "Customer cannot be null")
    private Long customerId;
    
    @NotNull(message = "Product cannot be null")
    private Long productId;

    @NotNull(message = "Store cannot be null")
    private Long storeId;
    
    @NotNull(message = "Rate cannot be null")
    @Range(min = 1,max = 5)
    private Integer rating;

    private String comment;

    public Review() {}
    public Review(Long customerId, Long productId, Long storeId, Integer rating, String comment) {
        this.customerId = customerId;
        this.productId = productId;
        this.storeId = storeId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public Long getProductId() {
        return productId;
    }
    public Long getStoreId() {
        return storeId;
    }
    public Integer getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
