package com.pismo.transactionroutine.domain.validator.impl;

import com.pismo.transactionroutine.domain.validator.IValidator;
import com.pismo.transactionroutine.exception.FailedValidationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NegativeAmountTransactionTypeValidation implements IValidator {

    private static final List<String> allowedTransactionTypes = Arrays.asList("Normal Purchase",
            "Purchase with installments", "Withdrawal");

    @Override
    public void validate(@NonNull final String description, @NonNull final BigDecimal amount) {

        if (allowedTransactionTypes.contains(description) && !isAmountNegative(amount)) {
            throw new FailedValidationException("Only negative amount is allowed for given transaction type");
        }
    }

    private boolean isAmountNegative(@NonNull final BigDecimal amount) {

        return amount.compareTo(new BigDecimal("0")) < 0;
    }
}
