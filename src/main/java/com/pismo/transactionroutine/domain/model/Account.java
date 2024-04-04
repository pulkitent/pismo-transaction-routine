package com.pismo.transactionroutine.domain.model;

import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.http.response.GetAccountResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    public static Account toModel(@NonNull final AccountCreationRequest accountCreationRequest) {

        return new Account(null, accountCreationRequest.getDocumentNumber());
    }

    public GetAccountResponse toResponse() {

        return new GetAccountResponse(this.getId(), this.getDocumentNumber());
    }
}
