package com.soultotec.financialservice.application.providers;

import com.soultotec.financialservice.application.core.TransactionOperationService;

public interface TransactionOperationProvider {

    TransactionOperationService provide() throws ClassNotFoundException;
}
