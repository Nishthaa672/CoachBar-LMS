#Library Management System(LMS)- CoachBar

This project is a Spring Boot Application for managing a library system. It provides RESTful APIs for performing CRUD operations on entity book.

...

##Features
-**CRUD Operations**- Add, View, Update and Delete books.
-**Validation**- Request payload validation using JSR 380 annotations.
-**Global Exception Handling**- Handles errors and returns meaningful error messages.
-**Swagger Integration**- API Documentation and Testing.
-**Spring Security**- Basic Authentication for endpoints.

...

##Technologies Used

-Java 21
-Spring Boot 3.4.0
-Spring Data JPA
-Spring Security
-PostgreSQL
-Swagger 2.7.0
-Maven

...

##Setup Instructions

### 1.Clone the Repository
'''bash
git clone
https://github.com/Nishthaa672/CoachBar-LMS.git
cd CoachBar-LMS

### 2. Setup the Database
CREATE DATABASE library_mgmt;

### 3. Update the database credentials in src/main/resources/application.properties
spring.datasource.username=your-username
spring.datasource.password=your-password

### 4. Build the Application
mvn clean install

### 5.Run the Application
mvn spring-boot:run

The Application will start at http://localhost:8080

...

##API Documentation
Swagger UI is available at http://localhost:8080/swagger-ui/index.html

...

##Testing the Application
-Add the Authorization Header: Basic Auth with valid username and password.
-Example credentials(configured in application.properties): 
    username:user
    password:password

...

##Sample API EndPoints
-Get All Books: /api/lms/books
-Get a Book by ID: /api/lms/books/{id}
-Add a New Book: /api/lms/book
-Update a Book: /api/lms/{id}
-Delete a Book: /api/lms/remove/{id}

...

##Running Tests
mvn test

...

##TroubleShooting
Common Issues
-Database Connection Error: Verify that database credentials in properties file are correct. Also, Ensure the PostgreSQL server is running.
-Swagger Not Loading: Check for any errors in console and also ensure that the springdoc-openapi dependencies are correctly added.
-401 Unauthorized: ENsure you are sending the correct Basic Auth header with the request.
