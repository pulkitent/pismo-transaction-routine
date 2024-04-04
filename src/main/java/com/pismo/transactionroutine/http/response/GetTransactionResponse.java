package com.pismo.transactionroutine.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class GetTransactionResponse {

    @Setter
    @JsonProperty("id")
    private Long id;

    @JsonProperty("account_id")
    private final Long accountId;

    @JsonProperty("operation_type_id")
    private final Long operationTypeId;

    @JsonProperty("amount")
    private final BigDecimal amount;

    @JsonProperty("event_date")
    private final LocalDateTime eventDate;
}
