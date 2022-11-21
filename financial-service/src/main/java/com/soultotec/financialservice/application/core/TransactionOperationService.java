package com.soultotec.financialservice.application.core;

import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.entities.TransactionType;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface TransactionOperationService {

    TransactionType type();
    FinancialTransactionEventEntity operateTransaction(FinancialTransactionEventEntity financialTransactionEventMono);
}
