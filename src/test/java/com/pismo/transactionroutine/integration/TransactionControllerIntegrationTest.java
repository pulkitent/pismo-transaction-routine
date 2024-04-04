package com.pismo.transactionroutine.integration;

import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/insert-transactions-table-test-data.sql")
class TransactionControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldCreateTransactionSuccessfullyAndReturnHttp201WithCorrectResponseBody() {

        //given
        var transactionCreationRequest = new TransactionCreationRequest(55555555L, 99999999L, new BigDecimal("-23.5"));

        //when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(transactionCreationRequest)
                .when()
                .post("/api/v1/transactions")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("account_id", Matchers.equalTo(55555555))
                .body("operation_type_id", Matchers.equalTo(99999999))
                .body("amount", Matchers.equalTo(-23.5F));
    }

    @Test
    void shouldCreateTransaction_ReturnsHttp400_WhenEmptyRequestBodySent() {

        //given when then
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/api/v1/transactions")
                .then()
                .statusCode(400);
    }
}
