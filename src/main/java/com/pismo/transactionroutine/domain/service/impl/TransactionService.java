package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.domain.model.Transaction;
import com.pismo.transactionroutine.domain.service.IOperationTypeService;
import com.pismo.transactionroutine.domain.service.ITransactionService;
import com.pismo.transactionroutine.domain.validator.IValidator;
import com.pismo.transactionroutine.repository.ITransactionRepository;
import com.pismo.transactionroutine.http.request.TransactionCreationRequest;
import com.pismo.transactionroutine.http.response.GetTransactionResponse;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    @Autowired
    private final IOperationTypeService operationTypeService;

    @Autowired
    private final ITransactionRepository transactionRepository;

    @Autowired
    private final List<IValidator> validators;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Override
    public GetTransactionResponse createTransaction(
            @NonNull final TransactionCreationRequest transactionCreationRequest) {

        final var description = operationTypeService.getDescriptionByOperationTypeId(
                transactionCreationRequest.getOperationTypeId());

        for (IValidator validator : validators) {
            validator.validate(description, transactionCreationRequest.getAmount());
        }

        final var savedTransaction = transactionRepository.save(Transaction.toModel(transactionCreationRequest));

        LOGGER.info("transaction created successfully with id: {}", savedTransaction.getId());
        return savedTransaction.toResponse();

    }
}
