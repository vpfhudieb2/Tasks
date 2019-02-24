How to run:
1. Clone or download the project.
2. Run it using this maven command
   mvn spring-boot:run

GET  http://localhost:8080/stock?productId={productId}
POST http://localhost:8080/updateStock
GET  http://localhost:8080/statistics?time=[today,lastMonth]
