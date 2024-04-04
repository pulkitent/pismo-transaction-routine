package com.pismo.transactionroutine.domain.service;

import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import com.pismo.transactionroutine.http.response.GetTransactionResponse;
import lombok.NonNull;

public interface ITransactionService {

    GetTransactionResponse createTransaction(@NonNull final TransactionCreationRequest transactionCreationRequest);

}
