package com.soultotec.financialservice.application.core.impl;

import com.soultotec.financialservice.application.core.TransactionOperationService;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.ports.jms.TransactionEvent;
import com.soultotec.financialservice.application.ports.services.FinancialService;
import com.soultotec.financialservice.application.ports.services.TransactionService;
import com.soultotec.financialservice.application.providers.TransactionOperationDelegate;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionEvent transactionEvent;
    private final TransactionOperationDelegate transactionOperationDelegate;
    private final FinancialService financialService;

    public TransactionServiceImpl(TransactionEvent transactionEvent, TransactionOperationDelegate transactionOperationDelegate, FinancialService financialService) {
        this.transactionEvent = transactionEvent;
        this.transactionOperationDelegate = transactionOperationDelegate;
        this.financialService = financialService;
    }

    @Override
    public FinancialTransactionEventEntity createFinancialTransaction(FinancialTransactionEventEntity eventEntity) {

        TransactionOperationService transactionOperationService = this.transactionOperationDelegate.getTransactionOperationService(eventEntity.getType());
        transactionOperationService.operateTransaction(eventEntity);
        return this.transactionEvent.sendMessage(eventEntity);
    }
}
