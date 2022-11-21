package com.soultotec.transactionservice.application.ports.clients;

import com.soultotec.transactionservice.application.entities.AccountHolderEntity;

public interface FinancialServiceClient {

    AccountHolderEntity getAccountHolderById(String accountHolderId);
}
