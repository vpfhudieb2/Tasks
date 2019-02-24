How to run:
1. Clone or download the project.
2. Run it using this maven command
   mvn spring-boot:run
3. APIs Paths:
   GET  http://localhost:8080/stock?productId=
   POST http://localhost:8080/updateStock
   GET  http://localhost:8080/statistics?time=
   
####################################################
Development Notes:
    * First I hope my understanding is correct so some sides of the tasks, I had some questions in my mind, but I had to make some assumptions on which I built
      my solution, you will find my assumptions explained in below sections.
    * Please note I added inline comments in the written code to explain the idea why I wrote it that way and what I try to solve.
    * For simplicity, and the short development time, no real database layer exists, I will store created objects in an in memory concurrent hash map. 
    * I tried to add some unit tests, but please note there should be much more unit tests in real development.
    * For the lack of time, I could not manage to add integration tests.
    * Because of storing product stocks in Java objects (as mentioned in the first point), the statistics calculation is a bit complex, 
      in a real development this will be a single fast non complex SQL query!
    * I still think more things can be done like: 
        ** Splitting some methods into smaller ones to it can be clearly unit-tested.
        ** Adding proper validation to the received requests.
        ** Clean naming convention (specially in DTO package), like adding common postfix for related classes.
        ** Exception and Exception handling
        ** Logging
####################################################
My Thoughts:
----------------
Concurrency
----------------
1. The clearly mentioned concurrency requirement in updateStock API is solved in my code, please see (/updateStock API) section below point 4
2. We still have a concurrency issue in the APIs which query stocks (Specially statistics API when it uses "today" statistics), there could be
   a chance that while calculating the today statistics, new values to be updated, this can be solved in same way as mentioned in (/stock API)
   point 2.
----------------
/updateStock API
----------------
1. Each product has many stock entries related to it (1:M relation)
2. This API can be called to insert a product and an initial stock for first time.
3. Every time this method is called, sold amount will be calculated using the received quantity in the new stock, and the quantity in the last stock:
    2.1 New quantity is less than previous stock entry which means the product was sold (sold amount = the difference of the two values)
    2.2 Equals to the previous stock entry which means no change on the product. (sold amount = 0)
    2.3 More than the previous stock entry which means more stock was added (sold amount = 0)
4. Concurrency management to prevent similtaneous update of same product's stock:
    4.1: Each call to this API is processed in a separate thread.
    4.2: The implementation provides a class called StockLock which is responsible to give and store locks in a synchronized way before starting to update the stock
    4.3: To try to get the lock, the method synchrnize on the received productId from the request.
    4.4: When the thread is allowed to get into the synchronized block, it will check if there is a lock already exists for this productId
    4.5: If yes, it will try to take it (and it will fail because it is already in use) so the thread return with response 204
    4.6: If there is no lock for this productId, it will create one, store it in the map, lock it, and leave the synchornized block.
    4.7: The method continue to update the stock info of the product.
    4.8: in a finally block, the lock will be released, and removed from the map.
    4.9: This way of locking guarantees completely no concurrent update of the stock is allowed.
----------------
/stock API
----------------
1. Calling this API will always return the last stock for this product.
2. This method has a concurrency issue, that it can happen a user query a specific product which is being updated in same time, so the user may see stale value.
    2.1: This can be fixed, by using the same locking mechanism defined in updateStock so we check first if this product is being updated or not.
         Please note this is not implemented, I just share a side effect and a possible solution.
----------------
/statistics
----------------
1. We have for each stored productId a list of related stocks history each contains quantity and sold amount.
2. For getting top available products in a period of time, we should do the following:
    2.1: Query all products which has stocks in this period.
    2.2: It can happen that the same product has multiple stocks in this period, so we choose the stock with the highest quantity.
    2.3: For all retrieved distinct products stocks from previous step, we get the largest 3 entries.
3. For getting top sold products we should do the following:
    3.1: For each product, calculate the sum of soldAmount in each stock history in the specific period.
    3.2: We send back the first highest 3 products with larges sold amount.
4. Top sold result will exclude items which was not sold (soldAmount = 0)
