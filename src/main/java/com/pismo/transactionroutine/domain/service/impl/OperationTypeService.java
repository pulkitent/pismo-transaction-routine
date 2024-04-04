package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.exception.ResourceNotFoundException;
import com.pismo.transactionroutine.domain.service.IOperationTypeService;
import com.pismo.transactionroutine.repository.IOperationTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationTypeService implements IOperationTypeService {

    @Autowired
    private final IOperationTypeRepository operationTypeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationTypeService.class);

    @Override
    public String getDescriptionByOperationTypeId(@NonNull final Long operationTypeId) {

        final var operationsType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("operation type not found with id: %s", operationTypeId)));

        LOGGER.info("OperationType fetched successfully with id: {}", operationsType.getId());
        return operationsType.getDescription();
    }
}
