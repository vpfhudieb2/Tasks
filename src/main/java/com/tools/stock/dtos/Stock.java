package com.tools.stock.dtos;

import java.time.LocalDateTime;

public class Stock {

    private String id;

    private LocalDateTime creationTime;

    private Integer quantity;

    public Stock() {
    }
    
    public Stock(String id, LocalDateTime creationTime, Integer quantity) {
        this.id = id;
        this.creationTime = creationTime;
        this.quantity = quantity;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
