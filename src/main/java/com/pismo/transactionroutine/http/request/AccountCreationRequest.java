package com.pismo.transactionroutine.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationRequest {

    @JsonProperty("document_number")
    @Min(value = 1)
    @NotNull(message = "document_number is a mandatory field to create a account")
    private String documentNumber;

}
