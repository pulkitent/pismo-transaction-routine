package com.pismo.transactionroutine.repository;

import com.pismo.transactionroutine.domain.model.OperationsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationTypeRepository extends JpaRepository<OperationsType, Long> {

}
