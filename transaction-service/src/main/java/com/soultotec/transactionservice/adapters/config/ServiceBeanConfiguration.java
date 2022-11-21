package com.soultotec.transactionservice.adapters.config;

import com.soultotec.transactionservice.application.core.impl.TransactionServiceImpl;
import com.soultotec.transactionservice.application.mappers.DateMapper;
import com.soultotec.transactionservice.application.mappers.TransactionMapper;
import com.soultotec.transactionservice.application.ports.clients.FinancialServiceClient;
import com.soultotec.transactionservice.application.ports.jms.UpdateFinancialBalanceEvent;
import com.soultotec.transactionservice.application.ports.repositories.TransactionRepository;
import com.soultotec.transactionservice.application.ports.services.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository,
                                                 UpdateFinancialBalanceEvent updateFinancialBalanceEvent,
                                                 TransactionMapper transactionMapper,
                                                 FinancialServiceClient financialServiceClient) {

        return new TransactionServiceImpl(transactionRepository, updateFinancialBalanceEvent, transactionMapper, financialServiceClient);
    }

    @Bean
    public DateMapper dateMapper() {
        return new DateMapper();
    }
}
