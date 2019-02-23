/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.test.unit.services;

import com.tools.stock.dtos.UpdateStock;
import com.tools.stock.test.unit.AbstractUnitTest;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Faris
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class StockManagementTest extends AbstractUnitTest{
        
    @Test
    public void testUpdateProductStockFirstTime(){
        
        UpdateStock updateStockRequest = new UpdateStock();
        updateStockRequest.setId("Tomato-Stock-001");
        updateStockRequest.setProductId("Tomato");
        updateStockRequest.setQuantity(500);
        updateStockRequest.setTimestamp(LocalDateTime.now());

        given(inMemoryDB.getProduct(updateStockRequest.getProductId())).willReturn(null);
        
        boolean isUpdated = stockManagement.updateStockLocked(updateStockRequest);
        
        assertThat(isUpdated, is(true));
    }
}
