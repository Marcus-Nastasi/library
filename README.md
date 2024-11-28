# Library Manager Application

## About the project

Library Management System.

This is an application developed to facilitate the library management.

The application allows for all crud operations to books (with S3 integration for images), members, librarians and rents.

The application enables the registration, editing, and deletion of books, members and rents, by librarians, as well as the management, offering a robust API on the back end.

Additionally, the API is documented with Swagger API to make the available routes, payloads, and responses easy to understand.

## Entity model
![library drawio](https://github.com/user-attachments/assets/1bd27afc-0758-42ef-b12b-ebd9f9b1dc3c)

## Techs

### Architecture
- **Architecture**: Clean Architecture, Isolated domain

### Back-end
- **Framework**: Spring Framework
- **Language**: Java 21
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

**Make sure you have opened the ports 8080 and 5432 on your machine locally**

1. **Clone this repo:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/library.git

2. **Configure the 'application.properties' file on '/src/main/resources':**
   ```bash
    spring.application.name=library

    spring.datasource.url=jdbc:postgresql://localhost:5432/library
    spring.datasource.username= [your_pg_username]
    spring.datasource.password= [your_pg_password]
    spring.datasource.driver-class-name=org.postgresql.Driver

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.main.allow-circular-references=true

    # AWS S3 Image Bucket
    aws.region = [your_aws_region]
    aws.accessKeyId = [your_aws_access_key]
    aws.secretKey = [your_aws_secret_key]
    aws.bucket.name = [your_aws_bucketname]

    # spring security + jwt
    spring.security.token.secret = [your_token_secret]

3. **Run the application with Docker: Ensure you're in the project's root directory and execute Docker Compose to start all services automatically:**
    ```bash
    [sudo] docker-compose up --d

4. **Wait for the build to complete and access the application: Once the build is finished, the application will be available in your browser:**
   ```bash
    http://localhost:8080/
