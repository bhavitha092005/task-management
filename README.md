# Task Management API (Spring Boot + PostgreSQL + MongoDB)

## 📌 Overview

This is a secure RESTful backend application for managing tasks. It allows users to register, authenticate, and perform CRUD operations on their tasks.

The application ensures that users can only access and manage their own tasks using JWT-based authentication and strict authorization checks.

---

## 🚀 Features

* User Registration and Login (JWT Authentication)
* Secure Password Hashing using BCrypt
* Task CRUD Operations (Create, Read, Update, Delete)
* User-specific task isolation (Authorization)
* Integration with PostgreSQL (Users) and MongoDB (Tasks)
* Global Exception Handling with clean error responses

---

## 🏗️ Tech Stack

* Backend: Spring Boot
* Security: Spring Security, JWT
* Database:

  * PostgreSQL (User data)
  * MongoDB (Task data)
* Build Tool: Maven

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```
git clone <your-repo-link>
cd taskmanager
```

### 2. Configure application.properties

```
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=bhavitha1234@

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=mysecretkey
```

---

### 3. Start Databases

* Start PostgreSQL
* Start MongoDB

---

### 4. Run Application

Run the Spring Boot application from your IDE (STS)

---

## 🔐 Authentication

All protected endpoints require JWT token:

```
Authorization: Bearer <your_token>
```

---

## 📡 API Endpoints

### Auth APIs

* POST `/auth/register`
* POST `/auth/login`

---

### Task APIs (Protected)

* POST `/tasks` → Create Task
* GET `/tasks` → Get All Tasks
* GET `/tasks/{id}` → Get Task by ID
* PUT `/tasks/{id}` → Update Task
* DELETE `/tasks/{id}` → Delete Task

---

## 🔒 Security

* JWT-based authentication
* Stateless session management
* User-level authorization enforced
* Users cannot access tasks belonging to others

---

## 🧠 Design Decisions

* PostgreSQL is used for structured user data with strong consistency
* MongoDB is used for flexible task storage
* JWT enables stateless authentication
* Layered architecture improves maintainability and separation of concerns

---

## ⚠️ Error Handling

* Global exception handling implemented
* Clean JSON error responses
* Proper HTTP status codes (400, 401, 403, 404)

---

## 🎥 Demo

Include a short demo video showing:

* User registration and login
* Creating and managing tasks
* Authorization check (user cannot access another user’s task)

---

## 📌 Future Improvements

* Role-based access control (RBAC)
* Refresh tokens
* Pagination and filtering
* API documentation using Swagger

---
