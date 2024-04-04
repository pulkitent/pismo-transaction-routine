package com.pismo.transactionroutine.domain.validator.impl;

import com.pismo.transactionroutine.exception.FailedValidationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NegativeAmountTransactionTypeValidationUnitTest {

    @InjectMocks
    private NegativeAmountTransactionTypeValidation negativeAmountTransactionTypeValidation;

    @Test
    void validate_shouldThrowException_whenAmountIsPositiveAndDescriptionIsPurchaseType() {

        Assertions.assertThrows(FailedValidationException.class,
                () -> negativeAmountTransactionTypeValidation.validate("Normal Purchase", new BigDecimal("23.5")));

    }

    @Test
    void validate_shouldNotThrowException_whenAmountIsNegativeAndDescriptionIsPurchaseType() {

        Assertions.assertDoesNotThrow(
                () -> negativeAmountTransactionTypeValidation.validate("Normal Purchase", new BigDecimal("-23.5")));

    }
}
