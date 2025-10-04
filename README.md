# Student Library Management System

This is a backend application for managing a student library. It provides a RESTful API for handling books, authors, students, and library transactions. The system is designed with a clear, layered architecture to ensure maintainability and scalability.

## Features

* **Student Management:** Add, update, and manage student information.
* **Author Management:** Add and manage authors, linking them to books.
* **Book Management:** Add new books, including details like category and author.
* **Transaction Management:** Track book issuance and return transactions.
* **Card Management:** Associate library cards with students to manage their library privileges.

---

## Technologies Used

* **Java:** The core programming language.
* **Spring Boot:** The primary framework for building the application.
* **Maven:** For dependency management and building the project.
* **MySQL:** The relational database for storing application data.
* **JPA/Hibernate:** For object-relational mapping to interact with the database.

---

## Project Structure

The project is organized into several key packages, each with a specific responsibility:

* `controller`: Handles incoming HTTP requests and returns responses.
* `service`: Contains the core business logic.
* `repository`: Manages data access and communication with the database.
* `model`: Defines the data models (entities) for the application.
* `requestDTO`: Data Transfer Objects used for incoming request payloads.
* `converter`: Utility classes for converting between DTOs and model objects.
* `enums`: Defines enums like `CardStatus` and `Category`.

This layered approach separates concerns, making the codebase easier to understand, test, and maintain.

---

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 11 or higher
* Maven
* A running MySQL database instance

### Setup and Running the Application

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    cd your-repository-name
    ```

2.  **Configure the database:**
    Update the database connection properties in `src/main/resources/application.properties` with your MySQL credentials and database name.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3.  **Build and run the application:**
    Use Maven to compile and run the Spring Boot application.
    ```bash
    mvn spring-boot:run
    ```

The application will start on port `8080` (or the port specified in `application.properties`).

---

## API Endpoints

* `GET /students`: Get all students
* `POST /students`: Add a new student
* `GET /books`: Get all books
* `POST /books`: Add a new book
* `POST /transactions/issue`: Issue a book to a student
* `POST /transactions/return`: Return a book from a student
