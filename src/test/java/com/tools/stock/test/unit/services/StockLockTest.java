/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.test.unit.services;

import com.tools.stock.test.unit.AbstractUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Faris
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class StockLockTest extends AbstractUnitTest{
    
    @Test
    public void testOneLockIsGrantedOnSameProductId(){
        
        String productId = "testProduct";
        
        boolean firstLockForSameProduct = stockLock.tryGetLock(productId);
        boolean secondLockForSameProduct = stockLock.tryGetLock(productId);
        
        assertThat(firstLockForSameProduct, is(true));
        assertThat(secondLockForSameProduct, is(false));
        
    }

    @Test
    public void testGrantLockToSecondProductUpdate(){
        
        String productId = "testProduct2";
        
        boolean firstLockForProduct = stockLock.tryGetLock(productId);
        stockLock.releaseAndRemoveLock(productId);

        boolean secondLockForProduct = stockLock.tryGetLock(productId);
        stockLock.releaseAndRemoveLock(productId);

        assertThat(firstLockForProduct, is(true));
        assertThat(secondLockForProduct, is(true));
        
    }
}
