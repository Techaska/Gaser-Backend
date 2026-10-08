<p align="center">
  <img src="https://raw.githubusercontent.com/Techaska/Gaser/master/assets/gaser-banner.png" alt="Gaser: Vehicle Service Center Management System" width="100%">
</p>

<p align="center">
  A Java Spring Boot backend for managing customers, vehicles, service centers, mechanics, service jobs, service records, and authentication.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Backend-F2B705?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=153A47" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-Framework-F2B705?style=for-the-badge&logo=springboot&logoColor=white&labelColor=153A47" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring_Security-JWT-F2B705?style=for-the-badge&logo=springsecurity&logoColor=white&labelColor=153A47" alt="Spring Security">
  <img src="https://img.shields.io/badge/PostgreSQL-Database-F2B705?style=for-the-badge&logo=postgresql&logoColor=white&labelColor=153A47" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Status-In_Development-F2B705?style=for-the-badge&labelColor=153A47" alt="Status: In Development">
</p>

<p align="center">
  <a href="#about">About</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#authentication">Authentication</a> •
  <a href="#modules">Modules</a> •
  <a href="#api">API</a> •
  <a href="#service-flow">Service Flow</a> •
  <a href="#roadmap">Roadmap</a>
</p>

---

<a id="about"></a>

## 📌 About

Gaser is a vehicle service center management backend designed to digitize the workflow between customers, vehicles, mechanics, and service centers.

The system is being developed as a real-world Java backend using REST APIs, Spring Boot, Spring Security, JWT authentication, JPA/Hibernate, request validation, exception handling, and PostgreSQL.

The goal is to provide a structured backend that can support customer management, vehicle management, service operations, service history, notifications, and future frontend applications.

---

<a id="architecture"></a>

## 🏗️ Architecture

Gaser follows a layered backend architecture.

Requests enter through the REST controllers, pass through Spring Security and JWT authentication where required, then move through the service layer and repository layer before reaching PostgreSQL through JPA/Hibernate.

<p align="center">
  <img src="https://raw.githubusercontent.com/Techaska/Gaser/master/assets/gaser-architecture.png" alt="Gaser architecture diagram" width="100%">
</p>

### Request Flow

```text
Client / Postman
       ↓
REST Controller
       ↓
Spring Security
       ↓
JWT Authentication Filter
       ↓
Service Layer
       ↓
Repository Layer
       ↓
JPA / Hibernate
       ↓
PostgreSQL
```

<details>
<summary><b>Project structure</b></summary>

<pre>
Gaser
└── src
    └── main
        └── java
            └── com.P1.Gaser
                ├── Config
                │   ├── JwtAuthenticationFilter
                │   └── SecurityConfig
                │
                ├── Controllers
                ├── DTO
                ├── Entity
                ├── Exception
                ├── Repositories
                └── Services
</pre>

</details>

---

<a id="authentication"></a>

## 🔐 Authentication

Gaser currently uses JWT-based authentication with Spring Security.

### Authentication Flow

```text
User Login
    ↓
POST /auth/login
    ↓
AuthService
    ↓
Find user by Email / Phone
    ↓
BCrypt Password Verification
    ↓
JWT Token Generated
    ↓
Client Stores Token
    ↓
Bearer Token sent with protected requests
    ↓
JwtAuthenticationFilter
    ↓
JWT Validation
    ↓
SecurityContext
    ↓
Protected API
```

### Authentication Features

- JWT token generation
- JWT token validation
- BCrypt password hashing
- Login using email or phone number
- Stateless authentication
- Protected REST endpoints
- Role information included in JWT
- Invalid credentials handling
- Authentication filter using `OncePerRequestFilter`

Passwords are not intended to be stored as plain text.

---

## 🛠️ Tech Stack

| Technology | Purpose |
| --- | --- |
| **Java** | Backend development |
| **Spring Boot** | Application framework |
| **Spring Web** | REST APIs |
| **Spring Security** | Authentication and API security |
| **JWT** | Stateless authentication |
| **BCrypt** | Password hashing |
| **Spring Data JPA** | Database interaction |
| **Hibernate** | ORM |
| **PostgreSQL** | Relational database |
| **Jakarta Validation** | Request validation |
| **Lombok** | Reduces boilerplate code |
| **Maven** | Build and dependency management |
| **Postman** | API testing |
| **Git & GitHub** | Version control |

---

<a id="modules"></a>

## 📦 Modules

| Module | What it does |
| --- | --- |
| 🔐 **User & Authentication** | Handles user accounts, login, password hashing, and JWT authentication |
| 👤 **Customer** | Manages customer information |
| 🚗 **Vehicle** | Stores vehicle details |
| 🏢 **Service Center** | Stores service center information |
| 🔧 **Mechanic** | Manages mechanics assigned to service operations |
| 📋 **Service Job** | Tracks service visits and their lifecycle |
| 📝 **Service Record** | Stores work performed during service |
| 🔔 **Notification** | Designed for customer service notifications |

<details>
<summary><b>Vehicle fields</b></summary>

- Registration number
- Brand
- Model
- Vehicle type
- Manufacturing year
- Kilometers driven

</details>

<details>
<summary><b>Service Job fields</b></summary>

- Service reference
- Customer
- Vehicle
- Service center
- Mechanic
- Received date/time
- Service start time
- Completion time
- Service status

</details>

---

<a id="api"></a>

## 🔌 API Endpoints

### Authentication

| Method | Endpoint | Purpose | Authentication |
| --- | --- | --- | --- |
| `POST` | `/auth/login` | Login using email or phone | Public |

### Users

| Method | Endpoint | Purpose | Authentication |
| --- | --- | --- | --- |
| `POST` | `/users` | Create user | Protected |
| `GET` | `/users` | Get users | Protected |
| `GET` | `/users/{id}` | Get user by ID | Protected |
| `PUT` | `/users/{id}` | Update user | Protected |
| `DELETE` | `/users/{id}` | Delete user | Protected |

### Core Modules

| Endpoint | Module |
| --- | --- |
| `/customers` | Customers |
| `/vehicles` | Vehicles |
| `/service-centers` | Service centers |
| `/mechanics` | Mechanics |
| `/service-jobs` | Service jobs |
| `/service-records` | Service records |
| `/notifications` | Notifications |

APIs are tested using Postman.

---

<a id="service-flow"></a>

## 🔄 Service Flow

The planned service workflow follows the lifecycle of a vehicle entering a service center.

| Step | What happens |
| :---: | --- |
| 1 | Customer information is registered |
| 2 | Vehicle is registered |
| 3 | Service center receives the vehicle |
| 4 | Service job is created |
| 5 | Mechanic is assigned |
| 6 | Service begins |
| 7 | Service status is updated |
| 8 | Service record is created |
| 9 | Customer is notified |
| 10 | Vehicle is ready for collection |

---

## 🚧 Current Development

### Completed

- [x] User entity and repository
- [x] User registration
- [x] Email and phone validation
- [x] Duplicate email/phone validation
- [x] BCrypt password hashing
- [x] JWT authentication
- [x] Login using email or phone
- [x] JWT authentication filter
- [x] Stateless Spring Security configuration
- [x] Protected REST endpoints
- [x] Global exception handling
- [x] Authentication error handling
- [x] Customer and service-center relationship model
- [x] PostgreSQL persistence
- [x] API testing with Postman

### In Progress

- [ ] End-to-end service job workflow
- [ ] Service record management
- [ ] Role-based authorization
- [ ] Notification workflow
- [ ] Backend workflow improvements

---

<a id="roadmap"></a>

## 🔮 Roadmap

- [ ] 👥 Complete role-based access control
  - Customer access
  - Service center access
  - Protected resources based on ownership
- [ ] 🔎 Vehicle registration number search
- [ ] 📧 Email notifications
- [ ] 📱 SMS notifications
- [ ] 🧾 PDF invoice generation
- [ ] 🖥️ React frontend integration
- [ ] 🔗 Frontend-backend authentication integration
- [ ] ☁️ Cloud deployment
- [ ] 🧪 Automated integration testing

---

## 🎯 Project Objective

Gaser is being developed as a practical real-world backend project to demonstrate how a vehicle service center workflow can be converted into a structured software system.

The project focuses on:

`Java` · `Spring Boot` · `Spring Security` · `JWT` · `REST API Design` · `PostgreSQL` · `JPA/Hibernate` · `Validation` · `Exception Handling` · `Authentication` · `Software Architecture`

---

## 👨‍💻 Author

**Akash**  
Java Backend Developer | Spring Boot | PostgreSQL | REST APIs

<p>
  <a href="https://github.com/Techaska">
    <img src="https://img.shields.io/badge/GitHub-Techaska-F2B705?style=flat-square&logo=github&logoColor=white&labelColor=153A47">
  </a>
</p>

---

<p align="center">
  ⭐ If you find the project useful, feel free to explore the code and follow the development.
</p>
