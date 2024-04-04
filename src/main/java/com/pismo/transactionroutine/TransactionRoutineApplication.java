package com.pismo.transactionroutine;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "transaction-routine", version = "1.0", description = "Transaction routine service"))
public class TransactionRoutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionRoutineApplication.class, args);
	}

}
