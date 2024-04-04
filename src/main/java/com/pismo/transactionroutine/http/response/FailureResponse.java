package com.pismo.transactionroutine.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class FailureResponse {

    @Setter
    @JsonProperty("error_message")
    private String errorMessage;

}
