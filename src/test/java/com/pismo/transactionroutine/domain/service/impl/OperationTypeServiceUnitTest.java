package com.pismo.transactionroutine.domain.service.impl;

import com.pismo.transactionroutine.domain.model.OperationsType;
import com.pismo.transactionroutine.repository.IOperationTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OperationTypeServiceUnitTest {

    @InjectMocks
    private OperationTypeService operationTypeService;

    @Mock
    private IOperationTypeRepository operationTypeRepository;

    @Test
    void getDescriptionByOperationTypeId_shouldFetchDescriptionSuccessfully() {
        //given
        long id = 123L;
        var description = "Normal Purchase";
        var operationsType = new OperationsType(id, description);
        Mockito.when(operationTypeRepository.findById(id)).thenReturn(Optional.of(operationsType));

        //when
        var actualDescription = operationTypeService.getDescriptionByOperationTypeId(id);

        //then
        Assertions.assertEquals(description, actualDescription);
    }

    @Test
    void getDescriptionByOperationTypeId_shouldThrowException_whenNullIsGiven() {
        //given when then
        Assertions.assertThrows(NullPointerException.class,
                () -> operationTypeService.getDescriptionByOperationTypeId(null));
    }
}