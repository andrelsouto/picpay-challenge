package com.soultotec.transactionservice.adapters.outbound.repositories.impl;

import com.soultotec.transactionservice.adapters.outbound.repositories.SpringDataTransactionRepository;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.AccountHolderDomain;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionDomain;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.entities.TransactionEntity;
import com.soultotec.transactionservice.application.mappers.AccountHolderMapper;
import com.soultotec.transactionservice.application.mappers.TransactionMapper;
import com.soultotec.transactionservice.application.ports.repositories.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryAdapterImpl implements TransactionRepository {
    
    private final SpringDataTransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountHolderMapper accountHolderMapper;

    public TransactionRepositoryAdapterImpl(SpringDataTransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountHolderMapper accountHolderMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountHolderMapper = accountHolderMapper;
    }

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {

        TransactionDomain transaction = this.transactionMapper.transactionEntityToTransactionDomain(transactionEntity);
        this.transactionRepository.save(transaction);
    }

    @Override
    public FinancialStatementEntity getByAccountHolderId(String accountHolderId) {

        List<TransactionDomain> transactions = this.transactionRepository.findAllByAccountHolderId(accountHolderId);
        Map<AccountHolderDomain, List<TransactionDomain>> transactionMap = transactions.stream().collect(
                Collectors.groupingBy(TransactionDomain::accountHolder)
        );

        return this.transactionMapper.mapToFinancialStatementEntity(transactionMap);
    }
}
