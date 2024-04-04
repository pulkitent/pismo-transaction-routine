package com.pismo.transactionroutine.http.controller;

import com.pismo.transactionroutine.domain.service.IAccountService;
import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.http.response.GetAccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cardholder's account CRUD APIs")
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    @Autowired
    private final IAccountService accountService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create an account for a cardholder customer", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success scenario"),
            @ApiResponse(responseCode = "400", description = "Bad request scenario"),
            @ApiResponse(responseCode = "500", description = "Internal server error scenario")
    })
    public ResponseEntity<GetAccountResponse> createAccount(
            @RequestBody @NonNull @Valid final AccountCreationRequest accountCreationRequest) {

        LOGGER.info("create account request received for documentNumber: {}",
                accountCreationRequest.getDocumentNumber());

        final var account = accountService.createAccount(accountCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);

    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get an account for a cardholder customer by id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success scenario"),
            @ApiResponse(responseCode = "404", description = "Not found scenario"),
            @ApiResponse(responseCode = "500", description = "Internal server error scenario")
    })
    public ResponseEntity<GetAccountResponse> getAccountById(@PathVariable @NonNull @Valid final Long accountId) {

        LOGGER.info("get account request received for accountId: {}", accountId);

        final var account = accountService.findAccountById(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
}
