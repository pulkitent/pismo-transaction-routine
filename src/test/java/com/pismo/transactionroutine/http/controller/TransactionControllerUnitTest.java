package com.pismo.transactionroutine.http.controller;

import com.pismo.transactionroutine.domain.model.Transaction;
import com.pismo.transactionroutine.domain.service.ITransactionService;
import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class TransactionControllerUnitTest {

    @InjectMocks
    private TransactionController transactionController;
    @Mock
    private ITransactionService transactionService;

    @Test
    void createTransaction_shouldCreateTransactionSuccessfully_whenValidCreationRequestGiven() {
        //given
        var id = 123L;
        var documentNumber = "12345";
        var accountId = 123L;
        var operationTypeId = 456L;
        var amount = new BigDecimal("23.5");
        var transactionCreationRequest = new TransactionCreationRequest(accountId, operationTypeId, amount);
        var expectedAccountResponse = Transaction.toModel(transactionCreationRequest).toResponse();
        expectedAccountResponse.setId(id);
        Mockito.when(transactionService.createTransaction(transactionCreationRequest))
                .thenReturn(expectedAccountResponse);

        //when
        var actualAccountResponseObject = transactionController.createTransaction(transactionCreationRequest);

        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(201), actualAccountResponseObject.getStatusCode());
        var actualResponseBody = actualAccountResponseObject.getBody();
        Assertions.assertEquals(id, actualResponseBody.getId());
        Assertions.assertEquals(accountId, actualResponseBody.getAccountId());
        Assertions.assertEquals(amount, actualResponseBody.getAmount());
        Assertions.assertEquals(operationTypeId, actualResponseBody.getOperationTypeId());
    }
}
