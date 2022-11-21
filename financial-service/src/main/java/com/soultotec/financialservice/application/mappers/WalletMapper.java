package com.soultotec.financialservice.application.mappers;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.WalletDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.WalletDTOResponse;
import com.soultotec.financialservice.adapters.outbound.jms.domain.WalletEvent;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.WalletDomain;
import com.soultotec.financialservice.application.entities.WalletEventEntity;
import org.mapstruct.Mapper;

@Mapper
public interface WalletMapper {

    WalletEvent walletEventEntityToWalletEvent(WalletEventEntity walletEventEntity);
    WalletEventEntity walletDTORequestToWallet(WalletDTORequest walletDTORequest);
    WalletDomain walletToWalletEventDomain(WalletEventEntity walletEventEntity);
    WalletEventEntity walletDomainToWalletEntity(WalletDomain walletDomain);
    WalletDTOResponse walletToWalletDTOResponse(WalletEventEntity walletEventEntity);
}
