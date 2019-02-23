/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.services;

import com.tools.stock.dtos.StockStatistics;
import com.tools.stock.dtos.TopAvailableProduct;
import com.tools.stock.dtos.TopSellingProduct;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Faris
 */
public interface StockStatisticsManagement {
    
    StockStatistics getStatistics(String range);
    
    List<TopAvailableProduct> getTopAvailableProducts(LocalDateTime from, LocalDateTime to);
    
    List<TopSellingProduct> getTopSellingProducts(LocalDateTime from, LocalDateTime to);
}
