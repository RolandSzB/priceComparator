# Price Comparator – Smart Shopping Assistant

## Project Overview

This Spring Boot application helps users compare product prices and discounts across multiple grocery stores (e.g., Kaufland, Lidl, Penny).
The project reads data from CSV files and offers a web interface to:

- Search for products across all stores
- Build a shopping basket and automatically split it across stores to optimize for cost
- View grouped product offers including:
  - Top Discounts Today
  - Latest Added Discounts
  - All Products and Offers by Store

### Project Structure
<pre>``` src
├── main
│ ├── java
│ │ └── com.example.priceComparator
│ │ ├── controller Contains API and page controllers
│ │ ├── model Data models for products and discounts
│ │ ├── util Utility classes for reading CSV data
│ │ └── service 
│ ├── resources
│ │ ├── templates Thymeleaf HTML templates 
│ │ ├── static JS resources
│ │ └── offers CSV files: prices and discounts by store
└── test ``` </pre>


### How to Build and Run

#### Prerequisites
- Java 17+
- Maven 3.6+
- IDE (optional): IntelliJ / Eclipse

#### Steps
1. Clone the repository:
   bash
   git clone https://github.com/your-username/price-comparator.git
   cd price-comparator
2. Build the porject:
   mvn clean install
3. Run the SpringBoot app:
   mvn spring-boot:run
4. Visit:
   http://localhost:8080/offers/grouped -for latest and best discounts
   http://localhost:8080/search -for searching a product
   http://localhost:8080/basket -for optimized basket creating


## License

Copyright (c) 2025 Szilágyi-Benedek Roland
Oradea, Romania 

