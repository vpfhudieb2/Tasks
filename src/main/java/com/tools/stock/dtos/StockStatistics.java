/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.dtos;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Faris
 */
public class StockStatistics {
    
    private LocalDateTime requestTimestamp;
    
    private String range;
    
    private List<TopAvailableProduct> topAvailableProducts;
    
    private List<TopSellingProduct> topSellingProducts;

    public StockStatistics() {
    }

    public StockStatistics(LocalDateTime requestTimestamp, String range, List<TopAvailableProduct> topAvailableProducts, List<TopSellingProduct> topSellingProducts) {
        this.requestTimestamp = requestTimestamp;
        this.range = range;
        this.topAvailableProducts = topAvailableProducts;
        this.topSellingProducts = topSellingProducts;
    }

    public LocalDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public List<TopAvailableProduct> getTopAvailableProducts() {
        return topAvailableProducts;
    }

    public void setTopAvailableProducts(List<TopAvailableProduct> topAvailableProducts) {
        this.topAvailableProducts = topAvailableProducts;
    }

    public List<TopSellingProduct> getTopSellingProducts() {
        return topSellingProducts;
    }

    public void setTopSellingProducts(List<TopSellingProduct> topSellingProducts) {
        this.topSellingProducts = topSellingProducts;
    }
}
