# Library Manager Application

## About the project

Library Management System.

This is an application developed to facilitate the library management.

The application allows for all crud operations to books (with S3 integration for images), members, librarians and rents.

The application enables the registration, editing, and deletion of books, members and rents, by librarians, as well as the management, offering a robust API on the back end.

Additionally, the API is documented with Swagger API to make the available routes, payloads, and responses easy to understand.

## Entity model


## Techs

### Architecture
- **Clean Architecture**: Isolated domain, onion architecture

### Back-end
- **Framework**: Spring Framework
- **Language**: Java
- **Tests**: JUnit and Mockito

### Database
- **Database**: PostgreSQL

### API Documentation
- **Tool**: Swagger API

### Containers
- **Docker** and **Docker Compose**

## How to run

Follow the steps below to set up and run the project on your local machine.

## Prerequisites

- Git
- java 21 (JDK) e Maven
- Docker and Docker Compose

## Steps

**Make sure you have opened the ports 8080, 3000 and 5432 on your machine locally**

1. **Clone this repo:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/library.git

- **Configure the 'application.properties' file on '/src/main/resources':**
   ```bash
   spring.application.name=todos

   # Hibernate properties 
   spring.jpa.hibernate.show-sql=true
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=postgres
   spring.datasource.password=123
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   
   # Security config
   spring.security.token.secret = [your_token_secret].

4. **Run the application with Docker: Ensure you're in the project's root directory and execute Docker Compose to start all services automatically:**
    ```bash
    [sudo] docker-compose up --d

5. **Wait for the build to complete and access the application: Once the build is finished, the application will be available in your browser:**
   ```bash
    http://localhost:8080/

6. **You can access the API documentation created with Swagger at the route:**
   ```bash
    http://localhost:8080/swagger-ui/index.html
