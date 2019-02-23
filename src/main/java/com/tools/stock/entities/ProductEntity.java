package com.tools.stock.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductEntity {

    private String id;

    private StockEntity currentStock;

    private List<StockEntity> stockHistory;

    public ProductEntity() {
    }

    public ProductEntity(String id, StockEntity currentStock) {
        this.id = id;
        this.currentStock = currentStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockEntity getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(StockEntity currentStock) {
        this.currentStock = currentStock;
    }

    public List<StockEntity> getStockHistory() {
        if(this.stockHistory == null)
            this.stockHistory = new ArrayList<>();

        return this.stockHistory;
    }

    public void setStockHistory(List<StockEntity> stockHistory) {
        this.stockHistory = stockHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
