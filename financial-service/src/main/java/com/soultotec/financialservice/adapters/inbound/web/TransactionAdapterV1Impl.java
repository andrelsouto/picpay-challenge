package com.soultotec.financialservice.adapters.inbound.web;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.TransactionDTOResponse;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.mappers.FinancialTransactionMapper;
import com.soultotec.financialservice.application.ports.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionAdapterV1Impl implements TransactionAdapterV1 {

    private final TransactionService transactionService;
    private final FinancialTransactionMapper financialTransactionMapper;

    public TransactionAdapterV1Impl(TransactionService transactionService, FinancialTransactionMapper financialTransactionMapper) {
        this.transactionService = transactionService;
        this.financialTransactionMapper = financialTransactionMapper;
    }

    @Override
    public TransactionDTOResponse createTransaction(TransactionDTORequest transactionDTORequest) {
        FinancialTransactionEventEntity financialTransactionEventEntity =
                this.financialTransactionMapper.transactionDTORequestToFinancialTransactionEvent(transactionDTORequest);
        FinancialTransactionEventEntity financialTransaction =
                this.transactionService.createFinancialTransaction(financialTransactionEventEntity);
        return this.financialTransactionMapper.financialTransactionEntityEventToTransactioDTOResponse(financialTransaction);
    }
}
