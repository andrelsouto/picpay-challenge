package com.soultotec.financialservice.adapters.config;

import com.soultotec.financialservice.application.core.TransactionOperationService;
import com.soultotec.financialservice.application.core.impl.DepositTransactionOperationServiceImpl;
import com.soultotec.financialservice.application.core.impl.FinancialServiceImpl;
import com.soultotec.financialservice.application.core.impl.PaymentTransactionOperationServiceImpl;
import com.soultotec.financialservice.application.core.impl.TransactionServiceImpl;
import com.soultotec.financialservice.application.core.impl.WireTransferTransactionOperationImpl;
import com.soultotec.financialservice.application.core.impl.WithdrawTransactionOperationServiceImpl;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.jms.TransactionEvent;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import com.soultotec.financialservice.application.ports.services.FinancialService;
import com.soultotec.financialservice.application.ports.services.TransactionService;
import com.soultotec.financialservice.application.providers.TransactionOperationDelegate;
import com.soultotec.financialservice.application.providers.TransactionOperationDelegateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public FinancialService accountService(AccountHolderRepository accountHolderRepository) {

        return new FinancialServiceImpl(accountHolderRepository);
    }

    @Bean
    public TransactionService transactionService(TransactionEvent transactionEvent,
                                                 TransactionOperationDelegate transactionOperationDelegate) {

        return new TransactionServiceImpl(transactionEvent, transactionOperationDelegate);
    }

    @Bean
    public TransactionOperationDelegate transactionOperationDelegate(List<TransactionOperationService> operationServices) {

        return new TransactionOperationDelegateImpl(operationServices);
    }

    @Bean
    public TransactionOperationService wireTransferTransactionOperationService(AccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper) {

        return new WireTransferTransactionOperationImpl(accountHolderRepository, accountHolderMapper);
    }

    @Bean
    public TransactionOperationService withdrawTransactionOperationService(AccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper) {

        return new WithdrawTransactionOperationServiceImpl(accountHolderRepository, accountHolderMapper);
    }

    @Bean
    public TransactionOperationService paymentTransactionOperationService(AccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper) {

        return new PaymentTransactionOperationServiceImpl(accountHolderRepository, accountHolderMapper);
    }

    @Bean
    public TransactionOperationService depositTransactionOperationService(AccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper) {

        return new DepositTransactionOperationServiceImpl(accountHolderRepository, accountHolderMapper);
    }
}
