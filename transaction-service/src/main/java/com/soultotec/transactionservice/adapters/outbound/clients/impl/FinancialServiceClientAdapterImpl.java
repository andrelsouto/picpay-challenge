package com.soultotec.transactionservice.adapters.outbound.clients.impl;

import com.soultotec.transactionservice.adapters.outbound.clients.AccountHolderClient;
import com.soultotec.transactionservice.adapters.outbound.clients.FinancialServiceFeignClient;
import com.soultotec.transactionservice.application.entities.AccountHolderEntity;
import com.soultotec.transactionservice.application.mappers.AccountHolderMapper;
import com.soultotec.transactionservice.application.ports.clients.FinancialServiceClient;
import org.springframework.stereotype.Component;

@Component
public class FinancialServiceClientAdapterImpl implements FinancialServiceClient {

    private final FinancialServiceFeignClient financialServiceFeignClient;
    private final AccountHolderMapper accountHolderMapper;

    public FinancialServiceClientAdapterImpl(FinancialServiceFeignClient financialServiceFeignClient,
                                             AccountHolderMapper accountHolderMapper) {
        this.financialServiceFeignClient = financialServiceFeignClient;
        this.accountHolderMapper = accountHolderMapper;
    }

    @Override
    public AccountHolderEntity getAccountHolderById(String accountHolderId) {

        AccountHolderClient accountHolder = this.financialServiceFeignClient.getAccountHolderById(accountHolderId);
        return this.accountHolderMapper.accountHolderClientToAccountHolderEntity(accountHolder);
    }
}
