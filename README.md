# 🚀 Task Management API

### Backend Developer Internship Submission – Assignments 2 & 3

A secure and scalable Task Management REST API built using **Spring Boot**, featuring JWT authentication, PostgreSQL + MongoDB integration, task reminders, webhook automation, categories, tags, and advanced filtering.

This project demonstrates backend engineering beyond CRUD by combining authentication, hybrid database architecture, event-driven workflows, and clean maintainable code structure.

---

# 📌 Submission Links

## 🔗 GitHub Repository

https://github.com/bhavitha092005/task-management

## 🎥 Demo Video

https://drive.google.com/file/d/1E0KLcp7CPtAVzkpfDGa1M8YnfCAgr9nR/view?usp=sharing

# 📁 API Collection

The complete API request collection used for testing and demonstration is included in this repository.

**Collection File:**  
`TaskManagementAPI.json`

You can import this file into **Postman** or **Hoppscotch** to test all endpoints including:

- Authentication APIs
- User Profile API
- Task CRUD APIs
- Task Filtering APIs
- Category Management APIs
- Reminder & Webhook Demonstration Requests

# ✨ Key Features

- JWT Authentication & Authorization
- PostgreSQL + MongoDB Hybrid Database Design
- Secure User-Based Task Ownership
- Full Task CRUD Operations
- Dynamic Categories & Free-Form Tags
- Advanced Task Filtering
- Real-Time Reminder Scheduling
- Webhook Integration with Retry Logic
- Global Exception Handling
- Clean Layered Architecture

---

# 🛠 Tech Stack

| Layer | Technology |
|------|------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| SQL Database | PostgreSQL |
| NoSQL Database | MongoDB |
| ORM | Spring Data JPA |
| Password Hashing | BCrypt |
| Scheduler | ScheduledExecutorService |
| HTTP Client | RestTemplate |

---

# 📌 Project Overview

This backend system allows users to securely register, authenticate, and manage personal tasks while supporting advanced productivity features such as reminders, categories, tags, and webhook-based analytics.

Each user has isolated access to their own tasks, ensuring secure multi-user behavior.

---

# 🔐 Assignment 2 Features

## Authentication

- User Registration
- User Login with JWT Token
- Secure Password Hashing using BCrypt
- Protected APIs using Bearer Token
- User Profile Endpoint

## Task Management

Authenticated users can:

- Create Tasks
- View All Tasks
- View Single Task by ID
- Update Tasks (Partial Updates Supported)
- Delete Tasks

## Ownership Protection

Users cannot access, modify, or delete tasks belonging to other users.

## Validation & Error Handling

- Email validation
- Required field validation
- Global exception handling
- Proper HTTP status responses

---

# ⚡ Assignment 3 Features

## ⏰ Smart Reminder Scheduler

When a task is created or updated with a `dueDate`:

- Reminder automatically scheduled
- Triggered before deadline
- Logged in console
- Rescheduled if due date changes
- Cancelled if task completed
- Cancelled if task deleted

---

## 🗂 Categories & Tags

### Categories

Dynamic categories supported:

- Work
- Personal
- Urgent
- Study

Operations:

- Create Category
- View Categories
- Update Category
- Delete Category

### Tags

Tasks support multiple free-form tags:

```json id="2x7m4q"
["urgent", "backend", "clientA"]
🔎 Advanced Filtering
GET /tasks/filter?category=Work
GET /tasks/filter?tag=urgent
GET /tasks/filter?category=Work&tag=urgent
🌐 External Analytics Webhook

When task status changes to:

completed

System automatically sends POST request to configured webhook URL.

Includes:
Task ID
Title
User ID
Completion Timestamp
Retry Logic
Retry 3 times
Exponential backoff
🗄 Database Architecture
PostgreSQL Stores
Users
Categories
MongoDB Stores
Tasks
Tags
Due Dates
Flexible Task Metadata
📂 Project Structure
src/main/java/com/bhavitha/taskmanager/

├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
└── service
🔑 Authentication Usage

Use JWT token after login:

Authorization: Bearer your_token_here
📘 Main API Endpoints
Authentication
Method	Endpoint
POST	/auth/register
POST	/auth/login
User
Method	Endpoint
GET	/user/profile
Tasks
Method	Endpoint
POST	/tasks
GET	/tasks
GET	/tasks/{id}
PUT	/tasks/{id}
DELETE	/tasks/{id}
Filters
Method	Endpoint
GET	/tasks/filter?category=Work
GET	/tasks/filter?tag=urgent
GET	/tasks/filter?category=Work&tag=urgent
GET	/tasks/tags
Categories
Method	Endpoint
POST	/categories?name=Work
GET	/categories
PUT	/categories/{id}?name=Office
DELETE	/categories/{id}
⚙️ Local Setup
Clone Repository
git clone YOUR_GITHUB_REPOSITORY_LINK
cd YOUR_PROJECT_FOLDER
Configure application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.data.mongodb.uri=mongodb://localhost:27017/taskdb

jwt.secret=your_secret_key_here

webhook.url=https://webhook.site/your-url
Run Application
mvn spring-boot:run
🧠 Design Decisions
Why Spring Boot?

Chosen for:

Strong backend architecture
Security integration
Layered scalability
Multi-database support
Why PostgreSQL + MongoDB?
PostgreSQL for structured relational data
MongoDB for flexible task documents
Why In-Memory Scheduler?

Lightweight and suitable for assignment reminder processing.

🚀 Future Enhancements
Redis Queue for reminders
Email / SMS notifications
Swagger/OpenAPI docs
Docker deployment
Async webhook workers
Role-based access control
👨‍💻 Author

Bhavitha
Backend Developer Internship Submission
