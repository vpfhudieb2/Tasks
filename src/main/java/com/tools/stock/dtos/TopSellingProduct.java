/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.dtos;

/**
 *
 * @author Faris
 */
public class TopSellingProduct {
    
    private String productId;
    
    private Integer itemsSold;

    public TopSellingProduct() {
    }

    public TopSellingProduct(String productId, Integer itemsSold) {
        this.productId = productId;
        this.itemsSold = itemsSold;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(Integer itemsSold) {
        this.itemsSold = itemsSold;
    }
}
