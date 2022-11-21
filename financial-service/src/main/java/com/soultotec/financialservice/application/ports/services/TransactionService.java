package com.soultotec.financialservice.application.ports.services;

import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface TransactionService {

    FinancialTransactionEventEntity createFinancialTransaction(FinancialTransactionEventEntity eventEntityMono);
}
