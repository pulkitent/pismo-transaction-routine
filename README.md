# transaction-routine

This service purpose is to manage transaction for each operation done one by the customer & associate it their
respective account.

## Built With

* Java 17 - The programming language used
* Spring Boot 3.2.4 - The web framework used
* PostgreSQL - The SQL based database (transaction_routine)
* Lombok - Lombok is used to reduce boilerplate code for model/data objects,
* Gradle - Dependency & build management
* Spring doc openapi - API Documentation (spring-boot and swagger-ui)
* Docker (Colima) - Containerize the application
* Flyway - For database migrations
* Jacoco - For generating test reports

## Steps to run the application on local

1. Run `chmod +x run.sh` to provide execute permission
2. Run `./run.sh` to run the service

### API documentation (Swagger)

1. Start the service using above steps
2. Open `http://localhost:8080/swagger-ui/index.html` in your browser

### Setting up your local environment for development

1. Install Java 11
2. Install Gradle 7
3. Run `./gradlew clean test` to run the unit tests
4. Run `./gradlew clean integrationTest` to run the integration tests (Check setup steps in `Integration test setup`
   below)
4. Run `./gradlew clean build` to build the service
6. Run `./gradlew clean bootRun -Dspring.profiles.active=dev` to start service (make sure postgres service is running)
7. Run `./gradlew clean bootJar` to create fat JAR

### IntelliJ Setup

1. Add lombok plugin (Preferences > Plugins > Search for Lombok)
2. Enable annotation processing (Preferences > Build, Execution, Deployment > Compiler > Annotation Processors)

## Integration test setup

If running on a Mac and using colima to run docker, you'll have to set the following for the test containers to work.

```
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
export DOCKER_HOST="unix://${HOME}/.colima/docker.sock"
sudo ln -s $HOME/.colima/default/docker.sock /var/run/docker.sock
```

## Project structure

    .
    ├── build.gradle                     : Gradle build script with dependency management.
    ├── gradlew                          : Gradle Wrapper script (Unix).
    ├── README.md                        : This file you are reading right now.
    ├── settings.gradle                  : Project's Gradle settings.
    ├── .gitignore                       : List of files not to be tracked by GIT  
    ├── docker-compose.yml               : Proivde orchestration between application & postgres conatianers
    ├── Dockerfile                       : Steps to assemble applications image
    ├── run.sh                           : Shell script to run application
    ├──gradle                           
    │   └──wrapper
    │      ├──gradle-wrapper.jar         : Gradle wrapper JAR.
    │      └──gradle-wrapper.properties  : Local properties for Gradle wrapper.
    └── src                              : 
        ├── main                         : Main sources.
        └── test                         : Test sources.

## Sample CURL

To create a transaction

```
curl --location 'localhost:8080/api/v1/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "document_number": "1111"
}'

 curl --location 'localhost:8080/api/v1/transactions' \
--header 'Content-Type: application/json' \
--data '{
    "account_id": 1,
    "operation_type_id": 4,
    "amount": 123.56
}'
```