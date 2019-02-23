package com.tools.stock.services.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * This class is responsible to maintain and grant locks on each product. 
 * Locks are of type Semaphores, and are stored in a {@code ConcurrentHashMap} class,
 * where the key of each entry is the productId, and the value is the related Semaphore lock
 * of this productId.
 * This class contain methods to create and store a lock for a specific product, and to release
 * and remove the lock for the product.
 * 
 * @author Faris
 */
@Service
public class StockLock {

    //Stores the lock for each productId.
    private static final ConcurrentHashMap<String, Semaphore> PRODUCT_LOCKS = new ConcurrentHashMap<>();

    /**
     * Controls acquiring locks on a productId. It guarantees that only ONE
     * thread is allowed to take the lock on specific productId value, so that if 2 threads
     * will try to acquire the lock on same productId, one of them will be able to do so, and
     * the other will return false.
     * @param productId 
     * The productId value on which the lock will be used.
     * 
     * @return
     * true: if the thread could obtain lock on this productId
     * false: if the thread failed to obtain the lock on this productId.
     */
    public boolean tryGetLock(String productId){

        /*
          This is important to sync on productId value, if 2 threads are trying to execute
          this method in the same time on same productId, one of them will be granted to 
          be inside the sync block, create lock, and store the lock, then return true.
          When the next thread be inside the sync block, it will find the lock already
          in use, and will return false.
        */
        synchronized (productId){
            
            //Check if there is already a lock stored in the map for this productId
            Semaphore lock = PRODUCT_LOCKS.get(productId);//ReentrantLock can be used as well

            //No lock yet exists for this productId. create a new one, and store it in the map.
            if(lock == null){
                lock = new Semaphore(1);
                PRODUCT_LOCKS.put(productId, lock);
            }
            //Try to acquire the lock, no waiting here, if is not allowed, return false
            return lock.tryAcquire(1);
        }
    }

    /**
     * Releases the lock on specific productId, and remove it from the map.
     * This is important so next thread when it will try to get the lock it will work normally.
     * @param productId
     */
    public void releaseAndRemoveLock(String productId){

        synchronized (productId){

            Semaphore lock = PRODUCT_LOCKS.get(productId);
            if(lock != null){
                lock.release(1);//release any possible locked resources
                PRODUCT_LOCKS.remove(productId);//Important to remove it to save memory instead of keeping the lock even is not used
            }
        }
    }
}
