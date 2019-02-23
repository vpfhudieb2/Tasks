package com.tools.stock.controllers;

import com.tools.stock.dtos.Product;
import com.tools.stock.dtos.UpdateStock;
import com.tools.stock.entities.ProductEntity;
import com.tools.stock.services.StockManagement;
import com.tools.stock.utils.EntityToDTOUtil;
import com.tools.stock.utils.HATEOSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockManagement stockManagement;

    @PostMapping(
            path = "/updateStock",
            consumes = "application/json")
    public ResponseEntity updateStock(@RequestBody UpdateStock updateStock){

        boolean isUpdated = stockManagement.updateStockLocked(updateStock);
        
        if(isUpdated){
            return ResponseEntity.created(HATEOSUtil.getURILinkedToControllerMethod(this.getClass(), new Object[]{updateStock.getProductId()}, "getCurrentStock", String.class)).build();
        }    
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(path = "/stock",
            produces = "application/json")
    public ResponseEntity<Product> getCurrentStock(@RequestParam("productId") String productId){
        
        ProductEntity productEntity = stockManagement.getStock(productId);
        if(productEntity == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(EntityToDTOUtil.createProductFrom(productEntity));
    }
}
