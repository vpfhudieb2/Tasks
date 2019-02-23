package com.tools.stock.dtos;

import java.time.LocalDateTime;

public class Product {

    private String productId;

    private LocalDateTime requestTimestamp;

    private Stock stock;

    public Product() {
    }

    public Product(String productId, LocalDateTime requestTimestamp, Stock stock) {
        this.productId = productId;
        this.requestTimestamp = requestTimestamp;
        this.stock = stock;
    }
    
    
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
