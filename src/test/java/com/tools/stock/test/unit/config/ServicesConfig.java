/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.test.unit.config;

import com.tools.stock.services.StockManagement;
import com.tools.stock.services.impl.StockLock;
import com.tools.stock.services.impl.StockManagementBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author Faris
 */
@TestConfiguration
public class ServicesConfig {
    
    @Lazy
    @Bean
    public StockLock stockLock(){
        return new StockLock();
    }
    
    @Lazy
    @Bean
    public StockManagement stockManagement(){
        return new StockManagementBean();
    }
}
