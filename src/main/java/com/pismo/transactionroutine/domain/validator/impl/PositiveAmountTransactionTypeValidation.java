package com.pismo.transactionroutine.domain.validator.impl;

import com.pismo.transactionroutine.exception.FailedValidationException;
import com.pismo.transactionroutine.domain.validator.IValidator;
import java.math.BigDecimal;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositiveAmountTransactionTypeValidation implements IValidator {

    private static final List<String> allowedTransactionTypes = List.of("Credit Voucher");

    @Override
    public void validate(@NonNull final String description, @NonNull final BigDecimal amount) {

        if (allowedTransactionTypes.contains(description) && !isAmountPositive(amount)) {
            throw new FailedValidationException("Only positive amount is allowed for given transaction type");
        }
    }

    private boolean isAmountPositive(@NonNull final BigDecimal amount) {

        return amount.compareTo(new BigDecimal("0")) > 0;
    }
}
