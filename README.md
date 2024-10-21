## File Processing Service

This is a scalable REST API service built with Spring Boot that handles file processing and status tracking. The service provides two main endpoints:

- /send: Uploads a request containing file links for processing.
- /get: Fetches the status of the processed files based on a requestId.
The solution is designed for scalability using asynchronous processing with CompletableFuture.

### Features

- Asynchronous file processing to handle multiple file uploads.
- Persistent storage using a relational database
- Monitoring of application performance using Micrometer metrics.
- Thread pool configuration for efficient handling of concurrent requests.

### Endpoints

1. /send
   Description: Upload a request with file links for processing.

Request Body:
```
{
"requestId": "154d68301e1342f3bd91a0c143efdb3a",
"consumer": "consumerName",
"fileLinks": [
{ "fileLink": "0747D0E5-B96A-4204-9BE6-3744369019B1" },
{ "fileLink": "DB747201-78A9-4A8E-AEA8-BA301A4AF9CC" },
{ "fileLink": "86FDFE98-6E5C-436D-A655-F42497789775" }
]
}
```
Response: HTTP Status 200 with a confirmation message.

2. /get
   Description: Get the status of processed files based on the requestId.

Request Body:

```
{
"requestId": "154d68301e1342f3bd91a0c143efdb3a"
}
```
Response:

```
{
"requestId": "154d68301e1342f3bd91a0c143efdb3a",
"fileLinks": [
{ "fileLink": "0747D0E5-B96A-4204-9BE6-3744369019B1", "status": "done" },
{ "fileLink": "DB747201-78A9-4A8E-AEA8-BA301A4AF9CC", "status": "in_progress" },
{ "fileLink": "86FDFE98-6E5C-436D-A655-F42497789775", "status": "done" }
]
}
```
### Installation

#### Prerequisites
  - Java 17 or higher
  - Maven 3.6+
  - PostgreSQL (if used in production)
  #### Steps to run:
  Clone the repository:
 ```
  git clone https://github.com/HAnnFAnn/file-processing-app.git
  cd file-processing-app
  ```
  Build the project:
  ```
  mvn clean install
  ```
  Run the application:
  ```
  mvn spring-boot:run
  ```
  The application will start and be available at http://localhost:8080/api.

Configuration

Database Configuration
For testing, the application uses an in-memory H2 database. In production, you can configure PostgreSQL or any other relational database by updating application.yaml:

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/${POSTGRES_DB}
    username: ${POSTGRES_USER}
    password: ${POSTGRES_PASSWORD}
```
Asynchronous Configuration
The size of the thread pool for asynchronous processing can be configured in application.yaml:

```
async:
  executor:
    thread-core-pool-size: 10
    thread-max-pool-size: 50
    thread-queue-capacity: 100
```
