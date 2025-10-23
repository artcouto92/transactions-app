
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

git clone https://github.com/<your-username>/transactions-app.git

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