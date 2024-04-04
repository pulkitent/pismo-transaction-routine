package com.pismo.transactionroutine.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GetAccountResponse {

    @Setter
    @JsonProperty("id")
    private Long id;

    @JsonProperty("document_number")
    private final String documentNumber;
}
