package com.soultotec.financialservice.application.providers;

import com.soultotec.financialservice.application.entities.TransactionType;
import com.soultotec.financialservice.application.core.TransactionOperationService;

public interface TransactionOperationDelegate {

    TransactionOperationService getTransactionOperationService(TransactionType operation);
}
