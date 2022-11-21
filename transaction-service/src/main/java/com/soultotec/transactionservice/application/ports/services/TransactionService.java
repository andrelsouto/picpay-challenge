package com.soultotec.transactionservice.application.ports.services;

import com.soultotec.transactionservice.application.entities.TransactionEntity;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;

public interface TransactionService {

    void saveTransaction(TransactionEntity transactionEntity);
    FinancialStatementEntity getTransactionsByAccountHolderId(String accountHolderId);
}
