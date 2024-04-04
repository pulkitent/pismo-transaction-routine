package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.exception.ResourceNotFoundException;
import com.pismo.transactionroutine.domain.model.Account;
import com.pismo.transactionroutine.domain.service.IAccountService;
import com.pismo.transactionroutine.repository.IAccountRepository;
import com.pismo.transactionroutine.http.request.AccountCreationRequest;
import com.pismo.transactionroutine.http.response.GetAccountResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    @Autowired
    private final IAccountRepository accountRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Override
    public GetAccountResponse createAccount(@NonNull final AccountCreationRequest accountCreationRequest) {

        final var model = Account.toModel(accountCreationRequest);
        final var savedAccount = accountRepository.save(model);

        LOGGER.info("account created successfully with id: {}", savedAccount.getId());
        return savedAccount.toResponse();
    }

    @Override
    public GetAccountResponse findAccountById(@NonNull final Long accountId) {

        final var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("account not found with id: %s", accountId)));

        LOGGER.info("account fetched successfully with id: {}", account.getId());
        return account.toResponse();
    }
}
