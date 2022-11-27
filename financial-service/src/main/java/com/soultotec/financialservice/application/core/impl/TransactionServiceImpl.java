package com.soultotec.financialservice.application.core.impl;

import com.soultotec.financialservice.application.core.TransactionOperationService;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.ports.jms.TransactionEvent;
import com.soultotec.financialservice.application.ports.services.TransactionService;
import com.soultotec.financialservice.application.providers.TransactionOperationDelegate;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionEvent transactionEvent;
    private final TransactionOperationDelegate transactionOperationDelegate;

    public TransactionServiceImpl(TransactionEvent transactionEvent, TransactionOperationDelegate transactionOperationDelegate) {
        this.transactionEvent = transactionEvent;
        this.transactionOperationDelegate = transactionOperationDelegate;
    }

    @Override
    public FinancialTransactionEventEntity createFinancialTransaction(FinancialTransactionEventEntity eventEntity) {

        TransactionOperationService transactionOperationService = this.transactionOperationDelegate.getTransactionOperationService(eventEntity.getType());
        transactionOperationService.operateTransaction(eventEntity);
        return this.transactionEvent.sendMessage(eventEntity);
    }
}
