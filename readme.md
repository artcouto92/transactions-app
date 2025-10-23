
# 💳 WEX - Transaction Currency Converter API

  
This is a **Spring Boot application** developed as part of the **WEX Senior Software Engineer Assessment**.

It allows you to **store purchase transactions in USD** and **retrieve them converted** into other currencies using official exchange rates from the **U.S. Treasury Reporting Rates of Exchange API**.


---
  
## 🚀 Features

  
- Store a purchase transaction (description, date, and USD amount)

- Retrieve all stored transactions converted to a target currency

- Integrates with the [U.S. Treasury Exchange Rate API](https://fiscaldata.treasury.gov/datasets/treasury-reporting-rates-exchange/treasury-reporting-rates-of-exchange)

- In-memory persistence (no external DB required)

- Proper validation, error handling, and RESTful responses

  
---


## 🧩 Tech Stack


- **Java 21**

- **Spring Boot 3.5.7**

- **Maven**

- **RestTemplate** (for API calls)

- **JUnit 5** (for automated tests)

- **Lombok** (optional)

  

---


## ⚙️ Project Structure


```plaintext

src/

├── main/

│ ├── java/com/example/transactionsapp/

│ │ ├── TransactionsAppApplication.java

│ │ ├── controller/TransactionController.java

│ │ ├── service/TransactionService.java

│ │ ├── repository/TransactionRepository.java

│ │ └── model/Transaction.java

│ └── resources/

│ └── application.properties

└── test/

└── java/com/example/transactionsapp/TransactionsAppApplicationTests.java

```


---

  
## 🛠️ Setup & Run


### 1️⃣ Clone the repository

```bash

git clone https://github.com/artcouto92/transactions-app.git

cd transactions-app
```


### 2️⃣ Build the project
  
```bash

mvn clean package
```


### 3️⃣ Run the application

```bash

mvn spring-boot:run
```


### 3️⃣ Run the tests

```bash

mvn test
```

---

## 💻 API Usage

Once the application is running locally (default port **8080**),  
you can interact with the API using `curl`, Postman, or directly via browser.

### ➕ Create a Transaction (POST)

Endpoint:
```bash
curl -X POST "http://localhost:8080/transactions?description=Laptop&date=2025-10-23&amount=1200.50"
```

Response:
```
{
  "id": "b6f2e0df-0a9a-4a1b-a7e8-70a6c6fefb0a",
  "description": "Laptop",
  "date": "2025-10-23",
  "amountUSD": 1200.50
}
```

### 💱 Retrieve Transactions in Another Currency (GET)

Endpoint:
```bash
curl -X GET "http://localhost:8080/transactions/{currency}"
````

Response:
```
[
  {
    "id": "b6f2e0df-0a9a-4a1b-a7e8-70a6c6fefb0a",
    "description": "Laptop",
    "date": "2025-10-23",
    "usdAmount": 1200.50,
    "exchangeRate": 0.92,
    "convertedAmount": 1104.46
  }
]
```

💡 Example currencies supported by the API:  
> “EURO”, “REAL”, “POUND”, “YEN”, etc.


