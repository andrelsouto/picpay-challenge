package com.soultotec.financialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class FinancialServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialServiceApplication.class, args);
    }

}
