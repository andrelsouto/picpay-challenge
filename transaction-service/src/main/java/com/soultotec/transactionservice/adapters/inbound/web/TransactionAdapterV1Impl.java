package com.soultotec.transactionservice.adapters.inbound.web;

import com.soultotec.transactionservice.adapters.inbound.web.domains.TransactionDTOResponse;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.mappers.TransactionMapper;
import com.soultotec.transactionservice.application.ports.services.TransactionService;
import org.springframework.stereotype.Component;

@Component
public class TransactionAdapterV1Impl implements TransactionAdapterV1 {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionAdapterV1Impl(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTOResponse getTransactionTimeLine(String accountHolderId) {
        FinancialStatementEntity transactions = this.transactionService.getTransactionsByAccountHolderId(accountHolderId);
        return this.transactionMapper.financialTransacEntityToTransactionResponse(transactions);
    }
}
