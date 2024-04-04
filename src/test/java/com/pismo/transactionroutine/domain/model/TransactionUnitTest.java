package com.pismo.transactionroutine.domain.model;

import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionUnitTest {

    @Test
    void toModel_shouldReturnCorrectModel_whenCorrectRequestIsGiven() {
        //given
        var transactionCreationRequest = new TransactionCreationRequest(12345L, 12345L, new BigDecimal("23.5"));

        //when
        var actualTransaction = Transaction.toModel(transactionCreationRequest);

        //then
        Assertions.assertEquals(transactionCreationRequest.getAccountId(), actualTransaction.getAccountId());
        Assertions.assertEquals(transactionCreationRequest.getOperationTypeId(),
                actualTransaction.getOperationTypeId());
        Assertions.assertEquals(transactionCreationRequest.getAmount(), actualTransaction.getAmount());
    }

    @Test
    void toModel_shouldThrowException_whenNullIsGiven() {

        //given when then
        Assertions.assertThrows(NullPointerException.class, () -> Transaction.toModel(null));
    }

    @Test
    void toResponse_shouldReturnCorrectResponse_whenCorrectModelIsGiven() {
        //given
        var transaction = Transaction.builder()
                .id(123L)
                .accountId(46L)
                .operationTypeId(88L)
                .amount(new BigDecimal("23.9"))
                .eventDate(LocalDateTime.now())
                .build();

        //when
        var actualTransactionResponse = transaction.toResponse();

        //then
        Assertions.assertEquals(transaction.getId(), actualTransactionResponse.getId());
        Assertions.assertEquals(transaction.getAccountId(), actualTransactionResponse.getAccountId());
        Assertions.assertEquals(transaction.getOperationTypeId(), actualTransactionResponse.getOperationTypeId());
        Assertions.assertEquals(transaction.getAmount(), actualTransactionResponse.getAmount());
        Assertions.assertEquals(transaction.getEventDate(), actualTransactionResponse.getEventDate());
    }
}
