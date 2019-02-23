package com.tools.stock.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockEntity {

    private String productId;

    private String stockId;

    private LocalDateTime stockCreationTime;

    private Integer quantity;

    private Integer amountSold;

    public StockEntity() {
    }

    public StockEntity(String productId, String stockId, LocalDateTime stockCreationTime, Integer quantity) {
        this.productId = productId;
        this.stockId = stockId;
        this.stockCreationTime = stockCreationTime;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public LocalDateTime getStockCreationTime() {
        return stockCreationTime;
    }

    public void setStockCreationTime(LocalDateTime stockCreationTime) {
        this.stockCreationTime = stockCreationTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(Integer amountSold) {
        this.amountSold = amountSold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return productId.equals(that.productId) &&
                stockId.equals(that.stockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, stockId);
    }
}
