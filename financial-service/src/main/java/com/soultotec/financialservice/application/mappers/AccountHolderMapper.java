package com.soultotec.financialservice.application.mappers;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.AccountHolderDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.AccountHolderDTOResponse;
import com.soultotec.financialservice.adapters.outbound.jms.domain.AccountHolderEvent;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.AccountHolderDomain;
import com.soultotec.financialservice.application.entities.AccountHolder;
import com.soultotec.financialservice.application.entities.AccountHolderEventEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AccountHolderMapper {

    AccountHolderEvent accountHolderEventEntityToAccountHolderEvent(AccountHolderEventEntity accountHolderEvent);
    AccountHolderEventEntity accountHolderToAccountHolderEventEntity(AccountHolder accountHolder);
    AccountHolder accountHolderRequestToAccountHolder(AccountHolderDTORequest accountHolderRequest);
    AccountHolderDomain accountHolderToAccountHolderDomain(AccountHolder accountHolder);
    AccountHolder accountHolderDomainToAccountHolder(AccountHolderDomain accountHolderDomain);
    AccountHolderDTOResponse accountHolderDomainToAccountHolderDTOResponse(AccountHolder accountHolder);
}
