package com.pismo.transactionroutine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTransactionRoutineApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransactionRoutineApplication::main).with(TestTransactionRoutineApplication.class).run(args);
	}

}
