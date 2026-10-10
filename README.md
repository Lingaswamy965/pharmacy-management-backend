# 💊 Pharmacy Management System — Backend

A Java Spring Boot REST API backend for a Pharmacy Management System. The application manages medicines, customers, sales, inventory stock, user login, and pharmacy dashboard information using a MySQL relational database.

## 📌 Project Overview

The backend provides REST APIs that connect the React frontend with the MySQL database. It implements business logic for medicine and customer management, sales processing, automatic stock reduction, and low-stock and expired-medicine monitoring.

## 🛠️ Technologies Used

- **Java** — Backend programming language
- **Spring Boot** — Backend application framework
- **Spring Data JPA / Hibernate** — Database persistence and ORM
- **MySQL** — Relational database
- **Maven** — Dependency management and build tool
- **REST APIs** — Communication between frontend and backend
- **Postman** — API testing

## ✨ Features

### 💊 Medicine Management
- Add new medicines
- Retrieve all medicines or a medicine by ID
- Update medicine details
- Delete medicines
- Track medicine name, category, batch number, manufacturer, price, quantity, and expiry date
- Retrieve low-stock medicines
- Retrieve expired medicines

### 👥 Customer Management
- Add customer records
- Retrieve customer information
- Update customer details
- Delete customer records

### 🧾 Sales and Billing
- Create sales associated with customers
- Store individual sale items
- Calculate the total sale amount
- Automatically reduce medicine stock after a successful sale
- Retrieve sales history and sale items

### 📊 Dashboard
Provides summary information, including:
- Total medicines
- Total customers
- Total sales
- Low-stock medicine count
- Expired-medicine count

### 🔐 User Management
- User registration
- Login endpoint for credential verification

### ⚠️ Inventory Alerts
- Identify medicines with low stock
- Identify medicines past their expiry date

## 🏗️ Backend Architecture

The application follows a layered architecture:

```text
React Frontend
      |
      | HTTP / REST API
      v
Spring Boot Controllers
      |
      v
Service Layer
      |
      v
Repository Layer
      |
      v
MySQL Database
```

### Main Package Structure

```text
src/main/java/com/pharmacy/pharmacybackend/
├── controller/
│   ├── TestController.java
│   ├── MedicineController.java
│   ├── CustomerController.java
│   ├── SaleController.java
│   ├── UserController.java
│   └── DashboardController.java
├── entity/
│   ├── Medicine.java
│   ├── Customer.java
│   ├── Sale.java
│   ├── SaleItem.java
│   └── User.java
├── exception/
│   └── GlobalExceptionHandler.java
├── repository/
├── service/
└── PharmacyBackendApplication.java
```

## 🗄️ Database

**Database name:** `pharmacy_management_db`

The main tables are:

| Table | Purpose |
|---|---|
| `users` | Stores user account records |
| `medicines` | Stores medicine and inventory information |
| `customers` | Stores customer details |
| `sales` | Stores sale records and totals |
| `sale_items` | Stores medicines included in each sale |

The sales and sale-items tables use relationships to associate sales with customers and medicines.

## 🔌 REST API Endpoints

Base URL:

```text
http://localhost:8080/api
```

### Medicine APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/medicines` | Retrieve all medicines |
| GET | `/medicines/{id}` | Retrieve a medicine by ID |
| POST | `/medicines` | Add a medicine |
| PUT | `/medicines/{id}` | Update a medicine |
| DELETE | `/medicines/{id}` | Delete a medicine |
| GET | `/medicines/low-stock` | Retrieve low-stock medicines |
| GET | `/medicines/expired` | Retrieve expired medicines |

### Customer APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/customers` | Retrieve all customers |
| GET | `/customers/{id}` | Retrieve a customer by ID |
| POST | `/customers` | Add a customer |
| PUT | `/customers/{id}` | Update a customer |
| DELETE | `/customers/{id}` | Delete a customer |

### Sales APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/sales` | Retrieve sales history |
| GET | `/sales/{id}` | Retrieve a sale by ID |
| GET | `/sales/{id}/items` | Retrieve items in a sale |
| POST | `/sales?customerId={id}` | Create a sale for a customer |

### User and Dashboard APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users/register` | Register a user |
| POST | `/users/login?username={username}&password={password}` | Verify login credentials |
| GET | `/dashboard/summary` | Retrieve dashboard statistics |

## ⚙️ Prerequisites

Install the following software before running the backend:

- Java Development Kit (JDK)
- MySQL Server
- Git
- Maven, or use the included Maven Wrapper
- Postman (optional, for API testing)

## 🚀 Setup and Installation

### 1. Clone the repository

Replace `YOUR_USERNAME` with your GitHub username.

```bash
git clone https://github.com/YOUR_USERNAME/pharmacy-management-backend.git
cd pharmacy-management-backend
```

### 2. Create the database

Open MySQL and execute:

```sql
CREATE DATABASE pharmacy_management_db;
```

The required tables must also be created according to the project's database schema.

### 3. Configure the database connection

Open:

```text
src/main/resources/application.properties
```

Configure your local MySQL connection:

```properties
spring.application.name=pharmacy-backend

spring.datasource.url=jdbc:mysql://localhost:3306/pharmacy_management_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Set `DB_USERNAME` and `DB_PASSWORD` in your local environment before running the application. Never commit real database credentials to GitHub.

**Database note:** `spring.jpa.hibernate.ddl-auto=none` means Hibernate will not automatically create the tables. Ensure the required schema exists before starting the application.

### 4. Run the backend

On Windows PowerShell or CMD, from the backend project directory:

```powershell
.\mvnw.cmd spring-boot:run
```

If the Maven Wrapper is unavailable, use an installed Maven version:

```bash
mvn spring-boot:run
```

Wait for the application to start on port `8080`.

### 5. Verify the application

Open:

```text
http://localhost:8080/
```

The root endpoint should return:

```text
Pharmacy Management System Backend is Running!
```

Test an API in your browser or Postman:

```text
GET http://localhost:8080/api/medicines
```

## 🧪 API Testing

Postman can be used to test the REST APIs independently of the frontend.

Recommended tests:
- Retrieve all medicines
- Retrieve a medicine by ID
- Add, update, and delete a medicine
- Retrieve customers
- Create a sale and verify stock reduction
- Retrieve sales history
- Check low-stock and expired-medicine endpoints
- Retrieve dashboard statistics
- Test valid and invalid login credentials

## 🔗 Frontend Integration

The backend is designed to work with the React frontend.

The frontend communicates with the backend using Axios and the following base URL during local development:

```text
http://localhost:8080/api
```

The React frontend and Spring Boot backend must both be running for the complete application to work locally.

## 🔒 Security Notes

- Keep database passwords and other credentials outside version control.
- Do not commit `.env` files containing secrets.
- The current login implementation should not be considered production-grade authentication.
- For production deployment, use password hashing, Spring Security, appropriate authorization, and secure session or token management.
- Configure HTTPS and restrict database access when deploying the application.

## 🔮 Future Enhancements

- Spring Security and role-based access control
- Secure password hashing
- PDF invoice generation
- Advanced sales reports and analytics
- Automated notifications for low stock and expiry
- Deployment to a cloud platform

## 👨‍💻 Project Information

**Project:** Pharmacy Management System  
**Backend:** Java Spring Boot  
**Database:** MySQL  
**API Style:** REST

---

*Developed as a full-stack pharmacy management project to simplify medicine inventory, customer records, sales processing, and stock monitoring.*
