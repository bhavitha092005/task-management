# 🚀 Task Management API

### Secure, Scalable & Event-Driven Backend System  
**Backend Developer Internship Submission – Assignments 2 & 3**

A production-style backend REST API built using **Spring Boot**, implementing secure JWT authentication, hybrid database architecture, task scheduling, webhook integrations, task categorization, and advanced filtering.

This project demonstrates backend engineering beyond basic CRUD by combining security, multi-database design, event-driven workflows, and maintainable architecture.

---

# ✨ Highlights

- JWT Authentication & Authorization  
- PostgreSQL + MongoDB Hybrid Database Design  
- Secure User-Based Task Ownership  
- Real-Time Reminder Scheduling  
- Webhook Integration with Retry Logic  
- Categories & Free-Form Tags  
- Advanced Task Filtering  
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

## 👤 Authentication

- User Registration
- Secure Password Hashing
- JWT Login Authentication
- Protected APIs using Bearer Token
- User Profile Endpoint

## ✅ Task Management

Authenticated users can:

- Create tasks
- View all personal tasks
- View single task by ID
- Update tasks (partial update supported)
- Delete tasks

## 🛡 Ownership Protection

Users cannot access, update, or delete tasks belonging to other users.

## 🧾 Validation & Error Handling

- Email validation
- Required field validation
- Global exception handling

Supported HTTP responses:

- 400 Bad Request  
- 401 Unauthorized  
- 403 Forbidden  
- 404 Not Found  

---

# ⚡ Assignment 3 Features

# ⏰ Smart Task Reminders

When a task is created or updated with a `dueDate`:

- Reminder automatically scheduled
- Triggered 1 hour before due date
- Logged to console
- Rescheduled if dueDate changes
- Cancelled if task completed early
- Cancelled if task deleted

---

# 🗂 Categories & Tags

## Categories

Users can dynamically manage categories such as:

- Work  
- Personal  
- Urgent  
- Study  

### Supported Operations

- Create Category
- View Categories
- Update Category
- Delete Category

## Tags

Tasks support multiple free-form tags:

```json
["backend", "urgent", "clientA"]
🔎 Advanced Filtering

Tasks can be filtered using:

GET /tasks/filter?category=Work
GET /tasks/filter?tag=urgent
GET /tasks/filter?category=Work&tag=urgent
🌐 External Analytics Webhook

When task status changes to:

completed

System automatically sends a POST request to a configurable webhook endpoint.

Payload Example
{
  "taskId": "69e26d7e39911b4dadec7b97",
  "title": "Complete Assignment",
  "userId": 8,
  "completedAt": "2026-04-17T23:00:00"
}
Retry Strategy

If webhook delivery fails:

Retry 3 times
Exponential backoff
🗄 Database Architecture
Why Hybrid Storage?

This project intentionally uses both relational and NoSQL databases.

PostgreSQL Stores:
Users
Categories
MongoDB Stores:
Tasks
Tags
Due dates
Flexible task metadata

This design balances structure and scalability.

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
📘 API Endpoints
Auth APIs
Method	Endpoint	Description
POST	/auth/register	Register user
POST	/auth/login	Login and get JWT
User API
Method	Endpoint
GET	/user/profile
Task APIs
Method	Endpoint
POST	/tasks
GET	/tasks
GET	/tasks/{id}
PUT	/tasks/{id}
DELETE	/tasks/{id}
Filtering APIs
Method	Endpoint
GET	/tasks/filter?category=Work
GET	/tasks/filter?tag=urgent
GET	/tasks/filter?category=Work&tag=urgent
GET	/tasks/tags
Category APIs
Method	Endpoint
POST	/categories?name=Work
GET	/categories
PUT	/categories/{id}?name=Office
DELETE	/categories/{id}
⚙️ Local Setup

1. Clone Repository
git clone https://github.com/yourusername/task-management.git
cd task-management

2. Configure application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.data.mongodb.uri=mongodb://localhost:27017/taskdb

jwt.secret=your_secret_key_here_32_chars_min

webhook.url=https://webhook.site/your-url


3. Run Application
mvn spring-boot:run
🧠 Design Decisions
Why Spring Boot?

Although the assignment suggested Node.js/Express.js, the backend requirements were framework-independent. Spring Boot was chosen for:

Enterprise-grade architecture
Strong security integration
Scalable layered structure
Easy multi-database support
Why PostgreSQL + MongoDB?
PostgreSQL manages structured relational data
MongoDB handles flexible task/tag data efficiently
Why In-Memory Scheduler?

Chosen for lightweight reminder processing aligned with assignment requirements.

🚀 Future Enhancements
Redis Queue for reminders
Email / SMS notifications
Dockerized setup
Swagger / OpenAPI docs
Async webhook workers
Admin dashboards
👨‍💻 Author

Bhavitha
Backend Developer Internship Submission
