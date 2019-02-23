package com.tools.stock.entities;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryDB {

    //Stores productId as key, and Product object as value, where all related stocks are retrieved from the root Product instance.
    private final ConcurrentHashMap<String, ProductEntity> PRODUCTS = new ConcurrentHashMap<>();

    public ProductEntity getProduct(String productId){
        return PRODUCTS.get(productId);
    }

    public void insertProduct(ProductEntity product){
        PRODUCTS.put(product.getId(), product);
    }

    public Collection<ProductEntity> getAllProducts(){
        return PRODUCTS.values();
    }
}
