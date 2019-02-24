/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.services.impl;

import com.tools.stock.dtos.StockStatistics;
import com.tools.stock.dtos.TopAvailableProduct;
import com.tools.stock.dtos.TopSellingProduct;
import com.tools.stock.entities.InMemoryDB;
import com.tools.stock.entities.StockEntity;
import com.tools.stock.services.StockStatisticsManagement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Faris
 */
@Service
public class StockStatisticsManagementBean implements StockStatisticsManagement{

    @Autowired
    private InMemoryDB inMemoryDB;
            
    @Override
    public StockStatistics getStatistics(String range) {
        
        LocalDateTime from;
        LocalDateTime to;
        //Base for calculating other dates
        LocalDateTime beginOfCurrentDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        
        switch(range){
            case "today" :
                from = beginOfCurrentDay;//Today midnight
                to = LocalDateTime.now();//Now
                break;
            case "lastMonth" :
                from = beginOfCurrentDay.minusMonths(1).withDayOfMonth(1);//1st day of last month
                to = beginOfCurrentDay.minusMonths(1).withDayOfMonth(from.getMonth().length(true)).withHour(23).withMinute(59).withSecond(59);//last day of last month
                break;
            default: 
                return null;
        }
        
        List<TopAvailableProduct> topAvailableProducts = getTopAvailableProducts(from, to);        
        List<TopSellingProduct> topSellingProducts = getTopSellingProducts(from, to);
        
        return new StockStatistics(LocalDateTime.now(), range, topAvailableProducts, topSellingProducts);
    }
    /*
    * Getting these values are easier when using real database, it will be a simple SQL query.
    * But because of using inMemory DB, I have to get these values using Java objects manipulation
    */
    @Override
    public List<TopAvailableProduct> getTopAvailableProducts(LocalDateTime from, LocalDateTime to) {

        /*
        This list contains Single stock entry for each product which represents the max available
        stock quantity of that product
        */
        List<StockEntity> maxQuantityStockEntities = new ArrayList<>();
        
        inMemoryDB
                .getAllProducts() //Get all stored products
                .stream()//Stream them
                .forEach( //Process the stock history for EACH product
                       p -> {
                            p
                                    .getStockHistory()//All product history
                                    .stream()//Stream them
                                    //Get the ones which are in the specified period
                                    .filter(s -> s.getStockCreationTime().isAfter(from) && s.getStockCreationTime().isBefore(to))
                                    //Get the stock with the max quantity
                                    .max(Comparator.comparing(StockEntity::getQuantity))
                                    //Add it to the (per-product) result.
                                    .ifPresent(s -> maxQuantityStockEntities.add(s));
                        });

        //We sort the list desc, get first max 3, convert them to DTO object, and return the result
        return maxQuantityStockEntities
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getQuantity(), s1.getQuantity()))
                .limit(3)
                .map(s -> new TopAvailableProduct(s.getStockId(), s.getStockCreationTime(), s.getProductId(), s.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TopSellingProduct> getTopSellingProducts(LocalDateTime from, LocalDateTime to) {
        
        List<TopSellingProduct> topSellingProducts = new ArrayList<>();
        
        inMemoryDB
                .getAllProducts()//Get all products
                .stream()//Stream them
                .forEach( //For each product, sum all sold amounts in the product stock history which falls in the time range.
                       p -> {
                            int sumAmountSoldInPeriod = p
                                    .getStockHistory()//Get all stock history for a product
                                    .stream()//Stream them
                                    //Get the ones in the range
                                    .filter(s -> s.getStockCreationTime().isAfter(from) && s.getStockCreationTime().isBefore(to))
                                    //Convert them to int stream on soldAmount
                                    .mapToInt(s -> s.getAmountSold())
                                    //Sum sold amount of matching stocks
                                    .sum();
                            
                            //Add the product with it's total sold amount
                            if(sumAmountSoldInPeriod > 0)
                                topSellingProducts.add(new TopSellingProduct(p.getId(), sumAmountSoldInPeriod));
                        });
        
        //Sort topeSellingProducts, get highest 3, and return them.
        return topSellingProducts
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getItemsSold(), p1.getItemsSold()))
                .limit(3)
                .collect(Collectors.toList());
    }
}
