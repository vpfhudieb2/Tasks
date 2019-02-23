/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author Faris
 */
public class TopAvailableProduct {
    
    private String id;
    
    private LocalDateTime timestamp;
    
    private String productId;
    
    private Integer quantity;

    public TopAvailableProduct() {
    }

    public TopAvailableProduct(String id, LocalDateTime timestamp, String productId, Integer quantity) {
        this.id = id;
        this.timestamp = timestamp;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
