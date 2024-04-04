package com.pismo.transactionroutine.domain.service;

import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.http.response.GetAccountResponse;
import lombok.NonNull;

public interface IAccountService {

    GetAccountResponse createAccount(@NonNull final AccountCreationRequest accountCreationRequest);

    GetAccountResponse findAccountById(@NonNull final Long accountId);
}
