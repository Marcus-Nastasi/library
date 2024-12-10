# Library Manager Application

## About the project

Library Management System.

This is an application developed to facilitate the library management, designed on clean architecture.

The application allows for all crud operations to books (with S3 integration for images), members, librarians and rents.

The application enables the registration, editing, and deletion of books, members and rents, by librarians, as well as the management, offering a robust API on the back end.

Additionally, the API is documented with Swagger API to make the available routes, payloads, and responses easy to understand.

## Entity model
![library drawio](https://github.com/user-attachments/assets/8aee0522-798b-49d9-bb71-9594cd3db7c1)

## Techs

### Architecture
- **Architecture**: Clean Architecture, Isolated domain

### Back-end
- **Framework**: Spring Framework
- **Language**: Java 21
- **Tests**: JUnit and Mockito

### Database
- **Database**: PostgreSQL

### Cache
- **Cache**: Redis

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

**Make sure you have opened the ports 8080 (application), 5432 (postgres) and 6379 (redis) on your machine locally**

1. **Clone this repo:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/library.git

2. **Configure the 'application.properties' file on '/src/main/resources':**
   ```bash
    spring.application.name = library

    spring.datasource.url = jdbc:postgresql://localhost:5432/library
    spring.datasource.username = [your_pg_username]
    spring.datasource.password = [your_pg_password]
    spring.datasource.driver-class-name = org.postgresql.Driver

    spring.jpa.hibernate.ddl-auto = update
    spring.jpa.show-sql = true

    # AWS S3 Image Bucket
    aws.region = [your_aws_region]
    aws.accessKeyId = [your_aws_access_key]
    aws.secretKey = [your_aws_secret_key]
    aws.bucket.name = [your_aws_bucketname]

    # spring security + jwt
    spring.security.token.secret = [your_token_secret]

    # Cache
    spring.cache.type = redis
    spring.redis.host = redis
    spring.redis.port = 6379
    spring.data.redis.repositories.enabled = false
    spring.cache.redis.time-to-live = 2d

3. **Run the application with Docker: Ensure you're in the project's root directory, access Docker folder, and execute Docker Compose to start all services automatically:**
    ```bash
    [sudo] docker-compose up --build -d

4. **Wait for the build to complete and access the application: Once the build is finished, the application will be available to requests by http clients, or swagger url:**
   ```bash
    http://localhost:8080/
    http://localhost:8080/swagger-ui/index.html

5. **Once you've built the images for the first time, you can create in the root folder of your computer's user, this bash script to run or stop the app easily (make sure you replace the [ ] marked fields with your own information):**
   ```bash
  #!/bin/bash

  usage() {
      echo "Uso: $0 -q"
      exit 1
  }

  if [ "$1" == "-q" ]; then
      action="stop"
  else
      action="start"
      access='Access http://localhost:8080/swagger-ui/index.html'
  fi

  cd [/your/path/to/the/app/docker/folder/]

  echo ''
  echo 'Insert your password to run...'
  echo ''

  [sudo] docker-compose $action

  echo ''
  echo "Application ${action}ed!"

  if [ "$action" != "stop" ]; then
      echo ''
      echo "${access}"
  fi

  cd [/your/path/to/user/root/]
