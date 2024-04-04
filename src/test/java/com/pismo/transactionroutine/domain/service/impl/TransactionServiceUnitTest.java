package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.domain.model.Transaction;
import com.pismo.transactionroutine.domain.validator.impl.NegativeAmountTransactionTypeValidation;
import com.pismo.transactionroutine.domain.validator.impl.PositiveAmountTransactionTypeValidation;
import com.pismo.transactionroutine.exception.FailedValidationException;
import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import com.pismo.transactionroutine.repository.ITransactionRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceUnitTest {

    private TransactionService transactionService;

    @Mock
    private OperationTypeService operationTypeService;

    @Mock
    private NegativeAmountTransactionTypeValidation negativeAmountTransactionTypeValidation;

    @Mock
    private PositiveAmountTransactionTypeValidation positiveAmountTransactionTypeValidation;

    @Mock
    private ITransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {

        var validators = Arrays.asList(negativeAmountTransactionTypeValidation, positiveAmountTransactionTypeValidation);
        transactionService = new TransactionService(operationTypeService, transactionRepository, validators);

    }

    @Test
    void createTransaction_shouldCreateAccountSuccessfully_whenAllValidationsPassed() {
        //given
        var id = 123L;
        var accountId = 123L;
        var operationTypeId = 123L;
        var amount = new BigDecimal("23.6");
        var transactionCreationRequest = new TransactionCreationRequest(accountId, operationTypeId, amount);
        var actualTransaction = Transaction.toModel(transactionCreationRequest);
        var expectedTransaction = Transaction.toModel(transactionCreationRequest);
        expectedTransaction.setId(id);
        Mockito.when(operationTypeService.getDescriptionByOperationTypeId(operationTypeId))
                .thenReturn("Voucher Credit");
        Mockito.when(transactionRepository.save(actualTransaction)).thenReturn(expectedTransaction);
        Mockito.doNothing().when(negativeAmountTransactionTypeValidation)
                .validate(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        Mockito.doNothing().when(positiveAmountTransactionTypeValidation)
                .validate(ArgumentMatchers.anyString(), ArgumentMatchers.any());

        //when
        var accountResponse = transactionService.createTransaction(transactionCreationRequest);

        //then
        Assertions.assertEquals(id,accountResponse.getId());
        Assertions.assertEquals(accountId,accountResponse.getAccountId());
        Assertions.assertEquals(operationTypeId,accountResponse.getOperationTypeId());
        Assertions.assertEquals(amount,accountResponse.getAmount());
    }

    @Test
    void createTransaction_shouldNotCreateAccountSuccessfully_whenNegativeAmountValidationsFailed() {
        //given
        var id = 123L;
        var accountId = 123L;
        var operationTypeId = 123L;
        var amount = new BigDecimal("-23.6");
        var transactionCreationRequest = new TransactionCreationRequest(accountId, operationTypeId, amount);
        var expectedTransaction = Transaction.toModel(transactionCreationRequest);
        expectedTransaction.setId(id);
        Mockito.when(operationTypeService.getDescriptionByOperationTypeId(operationTypeId)).thenReturn("Voucher Credit");
        Mockito.doThrow(FailedValidationException.class).when(negativeAmountTransactionTypeValidation).validate(ArgumentMatchers.anyString(), ArgumentMatchers.any());

        //when then
        Assertions.assertThrows(FailedValidationException.class,
                () -> transactionService.createTransaction(transactionCreationRequest));
    }

    @Test
    void createTransaction_shouldNotCreateAccountSuccessfully_whenPositiveAmountValidationsFailed() {
        //given
        var id = 123L;
        var accountId = 123L;
        var operationTypeId = 123L;
        var amount = new BigDecimal("-23.6");
        var transactionCreationRequest = new TransactionCreationRequest(accountId, operationTypeId, amount);
        var expectedTransaction = Transaction.toModel(transactionCreationRequest);
        expectedTransaction.setId(id);
        Mockito.when(operationTypeService.getDescriptionByOperationTypeId(operationTypeId)).thenReturn("Voucher Credit");
        Mockito.doNothing().when(negativeAmountTransactionTypeValidation).validate(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        Mockito.doThrow(FailedValidationException.class).when(positiveAmountTransactionTypeValidation).validate(ArgumentMatchers.anyString(), ArgumentMatchers.any());

        //when then
        Assertions.assertThrows(FailedValidationException.class,
                () -> transactionService.createTransaction(transactionCreationRequest));
    }

    @Test
    void createTransaction_shouldThrowException_whenNullIsGiven() {
        //given when then
        Assertions.assertThrows(NullPointerException.class, () -> transactionService.createTransaction(null));
    }
}
