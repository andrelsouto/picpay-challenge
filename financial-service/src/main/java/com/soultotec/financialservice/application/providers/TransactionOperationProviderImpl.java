package com.soultotec.financialservice.application.providers;

import com.soultotec.financialservice.application.entities.TransactionType;
import com.soultotec.financialservice.application.core.TransactionOperationService;

import java.util.List;

public class TransactionOperationProviderImpl implements TransactionOperationProvider {

    private final TransactionType operation;
    private final List<TransactionOperationService> operationServices;

    public TransactionOperationProviderImpl(TransactionType operation, List<TransactionOperationService> operationServices) {
        this.operation = operation;
        this.operationServices = operationServices;
    }

    @Override
    public TransactionOperationService provide() throws ClassNotFoundException {

        return this.operationServices .stream().filter(transactionService -> transactionService.type() == operation)
                .findFirst().orElseThrow(ClassNotFoundException::new);
    }
}
