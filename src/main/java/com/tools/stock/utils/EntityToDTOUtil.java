/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.utils;

import com.tools.stock.dtos.Product;
import com.tools.stock.dtos.Stock;
import com.tools.stock.entities.ProductEntity;
import com.tools.stock.entities.StockEntity;
import java.time.LocalDateTime;

/**
 *
 * @author Faris
 */
public class EntityToDTOUtil {
    
    public static Product createProductFrom(ProductEntity productEntity){
        
        if(productEntity == null)
            return null;
        
        return new Product(
                productEntity.getId(), 
                LocalDateTime.now(), 
                createStockFrom(productEntity.getCurrentStock()));
    }
    
    public static Stock createStockFrom(StockEntity stockEntity){
        
        if(stockEntity == null)
            return null;
        
        return new Stock(stockEntity.getStockId(), stockEntity.getStockCreationTime(), stockEntity.getQuantity());
    }
}
