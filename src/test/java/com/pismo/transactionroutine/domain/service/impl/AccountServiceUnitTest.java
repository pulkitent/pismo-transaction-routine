package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.domain.model.Account;
import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.repository.IAccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private IAccountRepository accountRepository;

    @Test
    void createAccount_shouldCreateAccountSuccessfully() {
        //given
        long id = 123L;
        var documentNumber = "12345";
        var accountCreationRequest = new AccountCreationRequest(documentNumber);
        var actualAccount = Account.toModel(accountCreationRequest);
        var expectedAccount = Account.toModel(accountCreationRequest);
        expectedAccount.setId(id);
        Mockito.when(accountRepository.save(actualAccount)).thenReturn(expectedAccount);

        //when
        var accountResponse = accountService.createAccount(accountCreationRequest);

        //then
        Assertions.assertEquals(id, accountResponse.getId());
        Assertions.assertEquals(documentNumber, accountResponse.getDocumentNumber());
    }

    @Test
    void createAccount_shouldThrowException_whenNullIsGiven() {
        //given when then
        Assertions.assertThrows(NullPointerException.class, () -> accountService.createAccount(null));
    }

    @Test
    void findAccountById_shouldFetchAccountSuccessfully() {
        //given
        long id = 123L;
        var documentNumber = "12345";
        var account = new Account(id, documentNumber);
        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        //when
        var accountResponse = accountService.findAccountById(id);

        //then
        Assertions.assertEquals(id, accountResponse.getId());
        Assertions.assertEquals(documentNumber, accountResponse.getDocumentNumber());
    }

    @Test
    void findAccountById_shouldThrowException_whenNullIsGiven() {
        //given when then
        Assertions.assertThrows(NullPointerException.class, () -> accountService.findAccountById(null));
    }
}
