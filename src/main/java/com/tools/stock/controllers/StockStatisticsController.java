/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.controllers;

import com.tools.stock.dtos.StockStatistics;
import com.tools.stock.services.StockStatisticsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faris
 */
@RestController
public class StockStatisticsController {
    
    @Autowired
    private StockStatisticsManagement stockStatisticsManagement;
    
    @GetMapping(path = "/statistics",
            produces = "application/json")
    public ResponseEntity<StockStatistics> getStockStatisticsFor(@RequestParam("time") String range){
        
        return ResponseEntity.ok(stockStatisticsManagement.getStatistics(range));
    }
}
