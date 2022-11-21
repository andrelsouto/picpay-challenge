package com.soultotec.transactionservice.application.mappers;

import com.soultotec.transactionservice.adapters.inbound.jms.domain.AccountHolderEvent;
import com.soultotec.transactionservice.adapters.outbound.clients.AccountHolderClient;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.AccountHolderDomain;
import com.soultotec.transactionservice.application.entities.AccountHolderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountHolderMapper {

    AccountHolderEntity accountHolderEventToAccountHolderEntity(AccountHolderEvent accountHolderEvent);

    AccountHolderDomain accountHolderEntityToAccountHolderDomain(AccountHolderEntity accountHolder);

    @Mapping(source = "wallet.amount", target = "walletBalance")
    AccountHolderEntity accountHolderClientToAccountHolderEntity(AccountHolderClient accountHolder);
    @Mapping(source = "accountHolder.accountHolderId", target = "id")
    AccountHolderEntity accountHolderDomainToAccountHolderEntity(AccountHolderDomain accountHolder);
}
