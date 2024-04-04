package com.pismo.transactionroutine.domain.model;

import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountUnitTest {

    @Test
    void toModel_shouldReturnCorrectModel_whenCorrectRequestIsGiven() {
        //given
        var accountCreationRequest = new AccountCreationRequest("12345");

        //when
        var actualAccount = Account.toModel(accountCreationRequest);

        //then
        Assertions.assertEquals(accountCreationRequest.getDocumentNumber(), actualAccount.getDocumentNumber());
    }

    @Test
    void toModel_shouldThrowException_whenNullIsGiven() {

        //given when then
        Assertions.assertThrows(NullPointerException.class, () -> Account.toModel(null));
    }

    @Test
    void toResponse_shouldReturnCorrectResponse_whenCorrectModelIsGiven() {
        //given
        var account = new Account(1234L, "12345");

        //when
        var actualAccountResponse = account.toResponse();

        //then
        Assertions.assertEquals(account.getId(), actualAccountResponse.getId());
        Assertions.assertEquals(account.getDocumentNumber(), actualAccountResponse.getDocumentNumber());
    }
}
