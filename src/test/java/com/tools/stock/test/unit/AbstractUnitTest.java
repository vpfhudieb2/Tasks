/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.test.unit;

import com.tools.stock.entities.InMemoryDB;
import com.tools.stock.services.StockManagement;
import com.tools.stock.services.impl.StockLock;
import com.tools.stock.test.unit.config.ServicesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Faris
 */
@ContextConfiguration(classes = {ServicesConfig.class})
public class AbstractUnitTest {

    @Autowired
    protected StockLock stockLock;
    
    @Autowired
    protected StockManagement stockManagement;
    
    @MockBean
    protected InMemoryDB inMemoryDB;

}
