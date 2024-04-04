package com.pismo.transactionroutine.domain.model;

import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import com.pismo.transactionroutine.http.response.GetTransactionResponse;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "operation_type_id", nullable = false)
    private Long operationTypeId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "event_date", nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime eventDate;

    public static Transaction toModel(@NonNull final TransactionCreationRequest transactionCreationRequest) {

        return Transaction.builder()
                .accountId(transactionCreationRequest.getAccountId())
                .operationTypeId(transactionCreationRequest.getOperationTypeId())
                .amount(transactionCreationRequest.getAmount())
                .build();
    }

    public GetTransactionResponse toResponse() {

        return GetTransactionResponse.builder()
                .id(this.getId())
                .accountId(this.getAccountId())
                .operationTypeId(this.getOperationTypeId())
                .amount(this.getAmount())
                .eventDate(this.getEventDate())
                .build();
    }
}
