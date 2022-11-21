package com.soultotec.financialservice.application.providers;

import com.soultotec.financialservice.application.entities.TransactionType;
import com.soultotec.financialservice.application.core.TransactionOperationService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TransactionOperationDelegateImpl implements TransactionOperationDelegate {

    private final List<TransactionOperationService> operationServices;

    public TransactionOperationDelegateImpl(List<TransactionOperationService> operationServices) {
        this.operationServices = operationServices;
    }

    @Override
    public TransactionOperationService getTransactionOperationService(TransactionType operation) {

        TransactionOperationProviderImpl transactionOperationProvider = new TransactionOperationProviderImpl(operation, operationServices);
        try {
            return transactionOperationProvider.provide();
        } catch (ClassNotFoundException e) {
            log.error("Unable to find implementation for type: {}", operation.name());
            throw new RuntimeException(e);
        }
    }
}
