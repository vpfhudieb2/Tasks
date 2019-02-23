package com.tools.stock.services;

import com.tools.stock.dtos.UpdateStock;
import com.tools.stock.entities.ProductEntity;

public interface StockManagement {

    boolean updateStockLocked(UpdateStock updateStock);
    
    ProductEntity getStock(String productId);
}
