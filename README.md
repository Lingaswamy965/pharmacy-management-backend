# 💊 Pharmacy Management System — Backend

A Java Spring Boot REST API backend developed for a Pharmacy Management System. It manages medicine inventory, customer records, sales and billing, user login, and pharmacy dashboard information using a MySQL relational database.

## 📌 Project Overview

The backend connects the React.js frontend with the MySQL database through REST APIs. It implements business logic for medicine and customer management, sales processing, automatic stock reduction after successful sales, and monitoring of low-stock and expired medicines.

The application follows a layered architecture using controllers, services, repositories, and entity classes.

## 🛠️ Technologies Used

- **Java** — Backend programming language
- **Spring Boot** — Backend application framework
- **Spring Data JPA** — Database interaction and persistence
- **Hibernate** — Object-relational mapping (ORM)
- **MySQL** — Relational database
- **Maven** — Dependency management and build tool
- **REST APIs** — Communication between frontend and backend
- **Postman** — API testing
- **Visual Studio Code** — Development environment

## ✨ Features Implemented

### 💊 Medicine Management
- Add, view, update, and delete medicines.
- Retrieve a medicine by its ID.
- Store medicine name, category, batch number, manufacturer, price, quantity, and expiry date.
- Retrieve medicines with low stock.
- Identify expired medicines.

### 👥 Customer Management
- Add new customer records.
- Retrieve all customers or a customer by ID.
- Update customer details.
- Delete customer records.
- Maintain customer names, phone numbers, email addresses, and addresses.

### 🧾 Sales and Billing
- Create sales associated with customers.
- Store individual items included in each sale.
- Calculate the total sale amount.
- Reduce medicine stock after a successful sale.
- Retrieve sales history.
- Retrieve sale details and associated sale items.

### 📊 Dashboard
Provides summary information for the frontend dashboard, including:
- Total medicines.
- Total customers.
- Total sales.
- Low-stock medicine count.
- Expired-medicine count.

### 🔐 User Management
- User registration endpoint.
- Login endpoint for credential verification.
- Integration with the frontend login form.

### ⚠️ Inventory Monitoring
- Retrieve medicines with low stock.
- Retrieve expired medicines.
- Provide inventory information for dashboard alerts.

## 🏗️ Backend Architecture

The backend follows a layered architecture.

```text
React.js Frontend
        |
        | HTTP Requests / REST APIs
        v
Spring Boot Controllers
        |
        v
Service Layer
(Business Logic)
        |
        v
Repository Layer
(Spring Data JPA)
        |
        v
Hibernate ORM
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
│   ├── MedicineRepository.java
│   ├── CustomerRepository.java
│   ├── SaleRepository.java
│   ├── SaleItemRepository.java
│   └── UserRepository.java
├── service/
│   ├── MedicineService.java
│   ├── CustomerService.java
│   ├── SaleService.java
│   ├── UserService.java
│   └── DashboardService.java
└── PharmacyBackendApplication.java
```

## 🗄️ Database Design

**Database name:** `pharmacy_management_db`

The application uses the following main tables:

| Table | Purpose |
|---|---|
| `users` | Stores user account records |
| `medicines` | Stores medicine and inventory details |
| `customers` | Stores customer information |
| `sales` | Stores completed sales and totals |
| `sale_items` | Stores individual medicines included in each sale |

The database relationships associate sales with customers and sale items with sales and medicines.

## 🔌 REST API Endpoints

**Base URL:** `http://localhost:8080/api`

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

Install the following software:

- Java Development Kit (JDK)
- MySQL Server
- Git
- Maven or the included Maven Wrapper
- Postman (optional, for API testing)
- Visual Studio Code (or another Java IDE)

## 🚀 Setup and Installation

### 1. Clone the repository

Replace `YOUR_USERNAME` with your GitHub username.

```bash
git clone https://github.com/YOUR_USERNAME/pharmacy-management-backend.git
cd pharmacy-management-backend
```

### 2. Configure MySQL

Start MySQL Server and create the database:

```sql
CREATE DATABASE pharmacy_management_db;
```

Ensure the required tables and relationships exist. The current configuration uses `spring.jpa.hibernate.ddl-auto=none`, so Hibernate will not automatically create the tables.

### 3. Configure the database connection

Open:

```text
src/main/resources/application.properties
```

Configure your local database connection:

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

Set `DB_USERNAME` and `DB_PASSWORD` in your local environment before starting the application. Never publish real database credentials on GitHub.

Use the database settings that match your local MySQL configuration.

### 4. Run the backend

On Windows PowerShell or Command Prompt, from the backend project directory, run:

```powershell
.\mvnw.cmd spring-boot:run
```

If the Maven Wrapper is unavailable and Maven is installed, run:

```bash
mvn spring-boot:run
```

Wait for Spring Boot to start on port `8080`.

### 5. Verify the application

Open the following URL in your browser:

`http://localhost:8080/`

The root endpoint should return:

```text
Pharmacy Management System Backend is Running!
```

You can also test the medicine API using a browser or Postman:

```http
GET http://localhost:8080/api/medicines
```

## 🧪 API Testing

Postman can be used to test the backend independently of the frontend.

Recommended tests include:

- Retrieve all medicines.
- Retrieve a medicine by ID.
- Add, update, and delete medicine records.
- Retrieve customer records.
- Create a sale and verify medicine stock reduction.
- Retrieve sales history and sale items.
- Test low-stock and expired-medicine endpoints.
- Retrieve dashboard statistics.
- Test valid and invalid login credentials.

The medicine retrieval endpoints were tested during development. Additional endpoints should be tested individually before production deployment.

## 🔗 Frontend Integration

The backend integrates with the React.js frontend through Axios.

The local frontend API configuration uses:

```text
http://localhost:8080/api
```

The frontend and backend run separately during local development. MySQL must also be running for database operations to work.

**Related project:** Pharmacy Management System — Frontend (React.js, JavaScript JSX, Vite, and Axios).

## 🔒 Security Notes

- Never commit database passwords, API keys, or other secrets to GitHub.
- The current login implementation is basic and should not be considered production-grade authentication.
- Use password hashing and Spring Security for production authentication.
- Implement appropriate authorization and secure session or token management.
- Use HTTPS and restrict database access when deploying the application.

## 🔮 Future Enhancements

- Spring Security integration.
- Secure password hashing.
- Role-based access control for administrators and pharmacists.
- PDF invoice generation.
- Advanced sales reports and analytics.
- Automated low-stock and expiry notifications.
- Cloud deployment.

## 🎯 Project Objective

The objective of this backend is to simplify pharmacy operations by providing reliable APIs for managing medicine inventory, customer information, sales, billing, and stock monitoring.

## 👨‍💻 Project Information

**Project:** Pharmacy Management System  
**Project Type:** Java Full Stack Web Application  
**Backend:** Java and Spring Boot  
**Persistence:** Spring Data JPA and Hibernate  
**Database:** MySQL  
**API Style:** REST APIs  
**Build Tool:** Maven

---

*Developed as the backend component of a Pharmacy Management System to improve the organization and efficiency of pharmacy inventory and sales operations.*
