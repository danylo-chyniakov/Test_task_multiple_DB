# Spring Boot Multiple Data Sources Example

This Spring Boot application demonstrates the use of multiple data sources with dynamic configuration. In current configuration the application interacts with two PostgreSQL databases, and one MySQL, and exposes a REST API to retrieve a list of users.

## Prerequisites

- Java 21
- Docker (for running DB containers in tests)

## Build and Run

To build the application, execute the following command in the project root:

```bash
./mvnw clean package
```

## To run the application, use:

```bash
./mvnw spring-boot:run
```
The application will be available at http://localhost:8080.

## Tests
Tests in this application use Testcontainers to dynamically create DB containers for integration testing. Make sure Docker is installed and running before running tests.

```bash
./mvnw test
```

## Main Logic
The application dynamically configures and interacts with multiple databases. The main components are:

DataSources Configuration: The DataSourceListConfiguration class configures a list of data sources based on properties provided in application.yml. Each data source corresponds to a some database.

UserRepository: The UserRepository class interacts with multiple instances of the UserDAO class, which encapsulates the logic for retrieving users from different databases.

UsersController: The REST controller exposes an endpoint to retrieve a list of users from all configured data sources.

## Configuration
Data source configuration is specified in the application.yml file, including database connection details and mapping information.

``` yaml
data-sources:
  - name: data-base-1
    strategy: postgres
    url: ${CONTAINER1.URL}
    table: users
    user: ${CONTAINER1.USERNAME}
    password: ${CONTAINER1.PASSWORD}
    mapping:
      id: user_id
      username: login
      name: first_name
      surname: last_name
  - name: data-base-2
    strategy: postgres
    url: jdbc:postgresql://localhost:5433/test2
    table: users2
    user: testuser2
    password: testpass2
    mapping:
      id: user_id2
      username: login2
      name: first_name2
      surname: last_name2
```
Adjust the properties based on your database configurations.