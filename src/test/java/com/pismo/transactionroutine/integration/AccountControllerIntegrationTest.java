package com.pismo.transactionroutine.integration;

import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.http.response.FailureResponse;
import com.pismo.transactionroutine.http.response.GetAccountResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/insert-accounts-table-test-data.sql")
class AccountControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldGetAccountById_ReturnsHttp200WithCorrectResponseBody() {

        //when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/accounts/999999998")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(999999998))
                .body("document_number", Matchers.equalTo("12345678900"));
    }

    @Test
    void shouldCreateAccount_ReturnHttps201WithCorrectResponseBody() {

        //given
        var documentNumber = "12345678900";
        var accountCreationRequest = new AccountCreationRequest(documentNumber);

        //when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(accountCreationRequest)
                .when()
                .post("/api/v1/accounts")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("document_number", Matchers.equalTo(documentNumber));
    }

    @Test
    void shouldGetAccountById_ReturnsHttp404_WhenAccountNotFound() {

        //given
        var accountId = "88";

        //when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("/api/v1/accounts/%s", accountId))
                .then()
                .statusCode(404);
    }

    @Test
    void shouldCreateAccount_ReturnsHttp400_WhenEmptyRequestBodySent() {

        //given when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/api/v1/accounts")
                .then()
                .statusCode(400);
    }
}
