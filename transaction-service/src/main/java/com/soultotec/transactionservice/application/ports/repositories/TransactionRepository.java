package com.soultotec.transactionservice.application.ports.repositories;

import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.entities.TransactionEntity;

public interface TransactionRepository {

    void saveTransaction(TransactionEntity transactionEntity);
    FinancialStatementEntity getByAccountHolderId(String accountHolderId);
}
