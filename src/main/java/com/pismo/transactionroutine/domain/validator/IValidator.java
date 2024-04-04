package com.pismo.transactionroutine.domain.validator;

import java.math.BigDecimal;
import lombok.NonNull;

public interface IValidator {

    void validate(@NonNull final String description, @NonNull final BigDecimal amount);

}
