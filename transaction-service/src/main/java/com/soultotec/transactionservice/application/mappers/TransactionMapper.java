package com.soultotec.transactionservice.application.mappers;

import com.soultotec.transactionservice.adapters.inbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.transactionservice.adapters.inbound.web.domains.TransactionDTOResponse;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.AccountHolderDomain;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionDomain;
import com.soultotec.transactionservice.application.entities.AccountHolderEntity;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import com.soultotec.transactionservice.application.entities.TransactionEntity;
import com.soultotec.transactionservice.application.entities.TransactionTimelineEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(TransactionMapperDecorator.class)
public interface TransactionMapper {

    @Mapping(source = "sender", target = "accountHolder")
    @Mapping(source = "amount", target = "transactionAmount")
    TransactionEntity financialTransactionEventToTransactionEntity(FinancialTransactionEvent financialTransactionEvent);

    @Mapping(source = "transactionAmount", target = "amount")
    @Mapping(source = "accountHolder.id", target = "accountHolder.accountHolderId")
    TransactionDomain transactionEntityToTransactionDomain(TransactionEntity transactionEntity);

    @Mapping(source = "transactionEntity.beneficiary", target = "accountHolder")
    @Mapping(source = "transactionEntity.id", target = "id")
    @Mapping(target = "newBalance", expression = "java(accountHolder.walletBalance().add(transactionEntity.transactionAmount()))")
    TransactionEntity transactionEntityToBeneficiaryTransactionEntity(TransactionEntity transactionEntity, AccountHolderEntity accountHolder);

    @Mapping(source = "accountHolder.id", target = "accountHolder.accountHolderId")
    TransactionDTOResponse financialTransacEntityToTransactionResponse(FinancialStatementEntity transactions);

    FinancialStatementEntity mapToFinancialStatementEntity(Map<AccountHolderDomain, List<TransactionDomain>> transactionMap);

    @Mapping(source = "createdDate", target = "transactionDateTime")
    TransactionTimelineEntity transactionDomainToTransactionTimelineEntity(TransactionDomain transactionDomain);
}
