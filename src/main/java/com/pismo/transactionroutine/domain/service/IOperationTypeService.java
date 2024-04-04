package com.pismo.transactionroutine.domain.service;

import lombok.NonNull;

public interface IOperationTypeService {

    String getDescriptionByOperationTypeId(@NonNull final Long accountId);
}
