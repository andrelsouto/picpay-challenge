package com.soultotec.transactionservice.application.core.impl;

import com.soultotec.transactionservice.application.entities.AccountHolderEntity;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.entities.TransactionEntity;
import com.soultotec.transactionservice.application.entities.UpdateBalanceEventEntity;
import com.soultotec.transactionservice.application.mappers.TransactionMapper;
import com.soultotec.transactionservice.application.ports.clients.FinancialServiceClient;
import com.soultotec.transactionservice.application.ports.jms.UpdateFinancialBalanceEvent;
import com.soultotec.transactionservice.application.ports.repositories.TransactionRepository;
import com.soultotec.transactionservice.application.ports.services.TransactionService;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UpdateFinancialBalanceEvent updateFinancialBalanceEvent;
    private final TransactionMapper transactionMapper;
    private final FinancialServiceClient financialServiceClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UpdateFinancialBalanceEvent updateFinancialBalanceEvent, TransactionMapper transactionMapper, FinancialServiceClient financialServiceClient) {
        this.transactionRepository = transactionRepository;
        this.updateFinancialBalanceEvent = updateFinancialBalanceEvent;
        this.transactionMapper = transactionMapper;
        this.financialServiceClient = financialServiceClient;
    }

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {

        this.transactionRepository.saveTransaction(transactionEntity);
        switch (transactionEntity.type()) {
            case WIRE_TRANSFER -> {
                this.updateAmount(transactionEntity.beneficiary().id(),
                        transactionEntity.transactionAmount());
                this.saveBeneficiaryTransaction(transactionEntity);
            }
            case DEPOSIT -> this.updateAmount(transactionEntity.accountHolder().id(),
                    transactionEntity.transactionAmount());
        }
    }

    @Override
    public FinancialStatementEntity getTransactionsByAccountHolderId(String accountHolderId) {
        FinancialStatementEntity transactions = this.transactionRepository.getByAccountHolderId(accountHolderId);
        if (Objects.isNull(transactions.accountHolder().id())) {
            throw new NotFoundExpection();
        }
        return transactions;
    }

    private void saveBeneficiaryTransaction(TransactionEntity transactionEntity) {

        AccountHolderEntity holder = this.financialServiceClient.getAccountHolderById(transactionEntity.beneficiary().id());
        TransactionEntity beneficiary = this.transactionMapper.transactionEntityToBeneficiaryTransactionEntity(transactionEntity, holder);
        this.transactionRepository.saveTransaction(beneficiary);
    }

    private void updateAmount(String accountId, BigDecimal amount) {
        this.updateFinancialBalanceEvent.sendMessage(UpdateBalanceEventEntity.builder()
                        .accountHolderId(accountId)
                        .amount(amount)
                .build());
    }
}
