# Expense Tracker

A web app to keep track of personal expenses. Add your expenses and browse your spending by year, month and day.

The backend is built with Java and Spring Boot, the frontend with HTML, CSS and JavaScript.

## Features

- Add an expense with a description, amount, category and date
- Delete expenses
- Categories: Shopping, Bills, Groceries, Rent, Car, Free time, Others
- Browse your spending by **year → month → day**, with the total shown for each level
- Input validation on both the frontend and the backend (e.g. no negative amounts, description limited to 100 characters)

## Tech Stack

**Backend**
- Java 21
- Spring Boot 
- Maven

**Frontend**
- HTML
- CSS
- JavaScript 

## How to Run

**Requirements:** JDK 21 and Maven

1. Clone the repository
   ```bash
   git clone https://github.com/mtosun-ch/expenseTracker.git
   cd expenseTracker
   ```

2. Start the backend
   ```bash
   mvn spring-boot:run
   ```
   The server runs on `http://localhost:8080`.

3. Open the frontend
   Open `frontend/index.html` in your browser 

> **Note:** The app uses an in-memory database, so all data is reset every time the backend restarts.

## API Overview

| Method | Endpoint                       | Description                         |
|--------|--------------------------------|-------------------------------------|
| GET    | `/api/expenses`                | Get all expenses                    |
| POST   | `/api/expenses`                | Add a new expense                   |
| PUT    | `/api/expenses/{id}`           | Update an existing expense          |
| DELETE | `/api/expenses/{id}`           | Delete an expense                   |
| GET    | `/api/account/month?date=...`  | Total spending of a month           |
| GET    | `/api/account/day?day=...`     | Total spending of a day             |

Example request:

```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{"amount": 20.50, "description": "Lunch", "category": "GROCERIES"}'
```

## Project Structure

```
expenseTracker/
├── src/main/java/expenseTracker/
│   ├── model/          # Expense and AccountBalance classes
│   ├── repository/     # Database access
│   └── controller/     # API endpoints
│   └── frontend/       # index.html, style.css, script.js
└── pom.xml
```