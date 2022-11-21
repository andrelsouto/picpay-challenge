package com.soultotec.transactionservice.application.mappers;

import com.soultotec.transactionservice.adapters.outbound.repositories.domains.AccountHolderDomain;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionDomain;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.entities.TransactionTimelineEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class TransactionMapperDecorator implements TransactionMapper {

    private TransactionMapper  transactionMapper;
    private AccountHolderMapper accountHolderMapper;

    @Autowired
    public void injectDependencies(TransactionMapper transactionMapper, AccountHolderMapper accountHolderMapper) {
        this.transactionMapper = transactionMapper;
        this.accountHolderMapper = accountHolderMapper;
    }

    @Override
    public FinancialStatementEntity mapToFinancialStatementEntity(Map<AccountHolderDomain, List<TransactionDomain>> transactionMap) {


        AccountHolderDomain.AccountHolderDomainBuilder builder = AccountHolderDomain.builder();

        transactionMap.keySet().stream().findFirst()
                .ifPresent(accountHolder -> {
                    builder.accountHolderId(accountHolder.accountHolderId());
                    builder.documentNumber(accountHolder.documentNumber());
                    builder.name(accountHolder.name());
                });

        List<TransactionTimelineEntity> timelineEntityList = transactionMap.values().stream().findFirst().orElseGet(ArrayList::new)
                .stream().map(this.transactionMapper::transactionDomainToTransactionTimelineEntity).toList();

        FinancialStatementEntity.FinancialStatementEntityBuilder transactionBuilder = FinancialStatementEntity.builder()
                .accountHolder(this.accountHolderMapper.accountHolderDomainToAccountHolderEntity(builder.build()))
                .transactions(timelineEntityList);
        transactionMap.values().stream().findFirst().ifPresent(transaction -> {
            if (transaction.size() > 0) {
                transactionBuilder.id(transaction.get(0).id());
            }
        });
        return transactionBuilder.build();
    }
}
