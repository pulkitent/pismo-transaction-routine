# pismo-transaction-routine

This service purpose is to manage transactions for each operation done by the customer & associate it with their
respective account.

## Built with

* Java 17 - The programming language used
* Spring Boot 3.2.4 - The web framework used
* PostgreSQL - The SQL-based database (transaction_routine)
* Lombok - Lombok is used to reduce boilerplate code for model/data objects,
* Gradle - Dependency & build management
* Spring doc open API - API Documentation (spring-boot and swagger-ui)
* Docker (Colima) - Containerize the application
* Flyway - For database migrations
* Jacoco - For generating test reports

## Steps to run the application on a local

1. Run `chmod +x run.sh` to provide execute permission
2. Run `./run.sh` to run the service (assuming the `brew` package manager is already installed, otherwise install it)

## API documentation (Swagger)

1. Start the service using the above steps
2. Open `http://localhost:8080/swagger-ui/index.html` in your browser

## Setting up your local environment for development

1. Install Java 11
2. Install Gradle 7
3. Run `./gradlew clean test` to run the unit tests
4. Run `./gradlew clean integrationTest` to run the integration tests (Check setup steps in `Integration test setup`
   below)
4. Run `./gradlew clean build` to build the service
6. Run `./gradlew clean bootRun -Dspring.profiles.active=dev` to start the service (make sure Postgres service is
   running)
7. Run `./gradlew clean bootJar` to create a fat JAR

## IntelliJ setup

1. Add Lombok plugin (Preferences > Plugins > Search for Lombok)
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

## Things I have tried to cover

* As a supporter of clean code I have tried to apply SOLID principles & 4 rules of simple design
* I have tried to add independent unit tests in **"Given When Then"** format with mocking wherever required & possible
* I also tried to publish a test coverage report using JACOCO
* I have tried to add integration tests in BBD format using testcontainers
* I have tried to integrate Swagger using springdoc openapi to publish my RESTful APIs documentation
* I have tried to Dockerize the application
* I have tried to add a run script for the seamless running of the application
* I have tried to add Flyway to do database schema migration
* I have tried to make logical & small commits with linear commit history

## Things I could have covered if given more time (or more complex business logic)

* I could have used concept of clean architecture by Uncle Bob (Robert C Martin) to structure my project
* I could have added ci-cd.yml to set up the CI/CD pipeline in the GitHub flow
* I could have implemented notification pattern as suggested by Martin Fowler by not throwing
  exceptions [ref-link](https://martinfowler.com/articles/replaceThrowWithNotification.html)
* I could have added method-level documentation using the Javadoc tool
* I could have made my RESTful endpoint HATEOAS compliant
  
