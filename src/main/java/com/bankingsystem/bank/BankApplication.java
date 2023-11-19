package com.bankingsystem.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		start(args);
	}

	public static void start(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
}
