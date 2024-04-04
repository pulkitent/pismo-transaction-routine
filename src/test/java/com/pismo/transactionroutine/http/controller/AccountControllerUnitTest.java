package com.pismo.transactionroutine.http.controller;

import com.pismo.transactionroutine.domain.model.Account;
import com.pismo.transactionroutine.domain.service.IAccountService;
import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class AccountControllerUnitTest {

    @InjectMocks
    private AccountController accountController;
    @Mock
    private IAccountService accountService;

    @Test
    void createAccount_shouldCreateAccountSuccessfully_whenValidCreationRequestGiven() {
        //given
        var id = 123L;
        var documentNumber = "12345";
        var accountCreationRequest = new AccountCreationRequest(documentNumber);
        var expectedAccountResponse = Account.toModel(accountCreationRequest).toResponse();
        expectedAccountResponse.setId(id);
        Mockito.when(accountService.createAccount(accountCreationRequest)).thenReturn(expectedAccountResponse);

        //when
        var actualAccountResponseObject = accountController.createAccount(accountCreationRequest);

        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(201), actualAccountResponseObject.getStatusCode());
        var actualResponseBody = actualAccountResponseObject.getBody();
        Assertions.assertEquals(id, actualResponseBody.getId());
        Assertions.assertEquals(documentNumber, actualResponseBody.getDocumentNumber());
    }

    @Test
    void getAccountById_shouldGetAccountSuccessfully_whenValidIdGiven() {

        //given
        var id = 123L;
        var documentNumber = "12345";
        var accountCreationRequest = new AccountCreationRequest(documentNumber);
        var expectedAccountResponse = Account.toModel(accountCreationRequest).toResponse();
        expectedAccountResponse.setId(id);
        Mockito.when(accountService.findAccountById(id)).thenReturn(expectedAccountResponse);

        //when
        var actualAccountResponse = accountController.getAccountById(id);

        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), actualAccountResponse.getStatusCode());
        var actualResponseBody = actualAccountResponse.getBody();
        Assertions.assertEquals(id, actualResponseBody.getId());
        Assertions.assertEquals(documentNumber, actualResponseBody.getDocumentNumber());
    }
}
