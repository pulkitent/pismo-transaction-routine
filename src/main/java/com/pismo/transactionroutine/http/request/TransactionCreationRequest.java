package com.pismo.transactionroutine.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreationRequest {

    @JsonProperty("account_id")
    @NotNull(message = "account_id is a mandatory field to create a transaction")
    private Long accountId;

    @JsonProperty("operation_type_id")
    @NotNull(message = "operation_type_id is a mandatory field to create a transaction")
    private Long operationTypeId;

    @JsonProperty("amount")
    @NotNull(message = "amount is a mandatory field to create a transaction")
    private BigDecimal amount;
}
