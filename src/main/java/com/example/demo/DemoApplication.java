package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(AccountRepository accountRepository, TransactionService transactionService) {
		return args -> {
			Account checking = accountRepository.save(new Account("Checking Account", 1000));
			Account savings = accountRepository.save(new Account("Savings Account", 5000));
			Account business = accountRepository.save(new Account("Business Account", 10000));

			transactionService.transfer(checking, savings, new BigDecimal(500));
		};
	}
}
