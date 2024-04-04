package com.pismo.transactionroutine.domain.validator.impl;

import com.pismo.transactionroutine.exception.FailedValidationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PositiveAmountTransactionTypeValidationUnitTest {

    @InjectMocks
    private PositiveAmountTransactionTypeValidation positiveAmountTransactionTypeValidation;

    @Test
    void validate_shouldThrowException_whenAmountIsNegativeAndDescriptionIsCreditType() {

        Assertions.assertThrows(FailedValidationException.class,
                () -> positiveAmountTransactionTypeValidation.validate("Credit Voucher", new BigDecimal("-23.5")));

    }

    @Test
    void validate_shouldNotThrowException_whenAmountIsNegativeAndDescriptionIsCreditType() {

        Assertions.assertDoesNotThrow(
                () -> positiveAmountTransactionTypeValidation.validate("Credit Voucher", new BigDecimal("23.5")));

    }
}
