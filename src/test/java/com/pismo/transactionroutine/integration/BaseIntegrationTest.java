package com.pismo.transactionroutine.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {

        RestAssured.baseURI = "http://localhost:" + port;
    }

    private static final PostgreSQLContainer<?> postgresTestContainer;

    static {
        postgresTestContainer = new PostgreSQLContainer<>("postgres:15-alpine")
                .withDatabaseName("transaction_routine")
                .withUsername("postgres")
                .withReuse(true)
                .waitingFor(Wait.forLogMessage("(.*)database system is ready to accept connections\n", 2));

        postgresTestContainer.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgresTestContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresTestContainer::getUsername);
        registry.add("spring.datasource.password", postgresTestContainer::getPassword);
    }
}
