package com.pismo.transactionroutine.http.controller;

import com.pismo.transactionroutine.domain.service.ITransactionService;
import com.pismo.transactionroutine.http.response.GetTransactionResponse;
import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
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
@Tag(name = "Transaction CRUD APIs")
@RequestMapping(value = "/api/v1/transactions")
public class TransactionController {

    @Autowired
    private final ITransactionService transactionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a transaction for a operation done by the customer", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success scenario"),
            @ApiResponse(responseCode = "400", description = "Bad request scenario"),
            @ApiResponse(responseCode = "500", description = "Internal server error scenario")
    })
    public ResponseEntity<GetTransactionResponse> createTransaction(
            @RequestBody @NonNull @Valid final TransactionCreationRequest transactionCreationRequest) {

        LOGGER.info("create transaction request received for accountId: {} & operationTypeId {}",
                transactionCreationRequest.getAccountId(), transactionCreationRequest.getOperationTypeId());

        final var transaction = transactionService.createTransaction(transactionCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
