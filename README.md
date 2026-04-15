# Task Management API (Spring Boot + PostgreSQL + MongoDB)

## 📌 Overview

This project is a secure RESTful backend application for managing tasks. It allows users to register, authenticate, and perform CRUD operations on their tasks.

The system ensures that each user can only access and manage their own tasks using JWT-based authentication and strict authorization.

---

## 🚀 Features

* User Registration and Login (JWT Authentication)
* Password hashing using BCrypt
* Secure JWT-based authentication
* User Profile endpoint
* Task CRUD operations (Create, Read, Update, Delete)
* User-level task isolation (authorization)
* Integration with PostgreSQL and MongoDB
* Global exception handling with proper HTTP status codes
* Server-side validation using annotations

---

## 🏗️ Tech Stack

* Backend: Spring Boot
* Security: Spring Security, JWT
* Databases:

  * PostgreSQL (User data)
  * MongoDB (Task data)
* Build Tool: Maven

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```
git clone https://github.com/bhavitha092005/task-management.git
cd taskmanagement
```

---

### 2. Configure application.properties

```
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=mysecretkeymysecretkeymysecretkey
```

---

### 3. Start Databases

* Start PostgreSQL server
* Start MongoDB server

---

### 4. Run Application

Run the Spring Boot application using STS or any IDE.

---

## 🔐 Authentication

All protected endpoints require JWT token in the header:

```
Authorization: Bearer <your_token>
```

---

## 📡 API Endpoints

### 🔑 Auth APIs

* **POST** `/auth/register` → Register a new user
* **POST** `/auth/login` → Login and receive JWT

---

### 👤 User API

* **GET** `/user/profile` → Get authenticated user details

---

### 📋 Task APIs (Protected)

* **POST** `/tasks` → Create a task
* **GET** `/tasks` → Get all tasks for logged-in user
* **GET** `/tasks/{id}` → Get a specific task
* **PUT** `/tasks/{id}` → Update a task (partial update supported)
* **DELETE** `/tasks/{id}` → Delete a task

---

## 🔒 Security

* JWT-based authentication
* Stateless session management
* User-level authorization enforced
* Users cannot access or modify tasks of other users

---

## 🧠 Design Decisions

* PostgreSQL is used for structured user data with strong consistency
* MongoDB is used for flexible task storage
* JWT enables stateless authentication
* Layered architecture (Controller → Service → Repository) improves maintainability
* User-task relationship handled using `userId`

---

## ⚠️ Error Handling

* Global exception handler implemented
* Returns clean JSON error responses
* Proper HTTP status codes:

  * 400 Bad Request
  * 401 Unauthorized
  * 403 Forbidden
  * 404 Not Found

---

## ✅ Validation

* Email validation using `@Email`
* Required fields using `@NotBlank`
* Basic validation for task inputs

---

## 📁 API Documentation

A Postman collection is included in the repository:

```
task-api.postman_collection.json
```

It contains all endpoints with sample request bodies.

---

## 🎥 Demo

The demo video demonstrates:

* User registration and login
* Creating and managing tasks
* Access control (user cannot access another user's task)
* Error handling and validation

---

## 📌 Future Improvements

* Role-based access control (RBAC)
* Refresh tokens for authentication
* Pagination and filtering for tasks
* Swagger/OpenAPI documentation

---
