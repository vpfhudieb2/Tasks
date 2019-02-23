package com.tools.stock.services.impl;

import com.tools.stock.dtos.UpdateStock;
import com.tools.stock.entities.InMemoryDB;
import com.tools.stock.entities.ProductEntity;
import com.tools.stock.entities.StockEntity;
import com.tools.stock.services.StockManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Process update and get stock operations
 * 
 * @author Faris
 */
@Service
public class StockManagementBean implements StockManagement {

    @Autowired
    private StockLock stockLock;

    @Autowired
    private InMemoryDB inMemoryDB;

    /**
     * This will make sure each update of a product stock NEVER happens concurrently for same productId.
     * This is guaranteed using {@code StockLock} class
     * 
     * @param updateStock
     * The new stock info for a product.
     * 
     * @return
     * True: if the stock could be stored successfully.
     * False: if there is another thread is already processing another stock for same productId
     */
    @Override
    public boolean updateStockLocked(UpdateStock updateStock) {
        
        //Initialize the lockAcquired to false which means lock on this productId could NOT be acquired
        boolean lockAcquired = false;
        //Try block so finally block is always executed to relase the lock
        try{
            //Try acquiring the lock from the StockLock object
            lockAcquired = stockLock.tryGetLock(updateStock.getProductId());

            //Return lock could NOT be acquired -> another thread has already locked this productId
            if(!lockAcquired)
                return false;

            //Do process the update request
            insertOrUpdateProductStock(updateStock);
            
            //Return lock was acquired, and update processed.
            return true;            
        }
        //Release resources
        finally{
            if(lockAcquired)
                stockLock.releaseAndRemoveLock(updateStock.getProductId());
        }
    }
    
    private void insertOrUpdateProductStock(UpdateStock updateStock) {

        StockEntity newStock = new StockEntity(updateStock.getProductId(), updateStock.getId(), updateStock.getTimestamp(), updateStock.getQuantity());

        ProductEntity product = inMemoryDB.getProduct(updateStock.getProductId());

        //No stock was inserted for this product ever, this will insert new product and the new stock
        if(product == null){
            newStock.setAmountSold(0);
            product = new ProductEntity(updateStock.getProductId(), newStock);

            inMemoryDB.insertProduct(product);
        }
        //New stock for an already exists product arrives, update the current stock, and update the stock history of this product
        else{
            StockEntity lastStock = product.getCurrentStock();

            if(lastStock.getQuantity() >= newStock.getQuantity()) //This means product was consumed since last stock update, calculate the sold amount
                newStock.setAmountSold(lastStock.getQuantity() - newStock.getQuantity());
            else
                newStock.setAmountSold(0);

            product.setCurrentStock(newStock);
        }
        product.getStockHistory().add(newStock);//Always add the new stock to the history so it appears in statistics.
    }

    /**
     *
     * @param productId
     * @return
     */
    @Override
    public ProductEntity getStock(String productId) {
        
        return inMemoryDB.getProduct(productId);
    }
}
