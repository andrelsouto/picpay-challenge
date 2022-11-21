package com.soultotec.financialservice.application.mappers;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.TransactionDTOResponse;
import com.soultotec.financialservice.adapters.outbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FinancialTransactionMapper {

    FinancialTransactionEvent financialTransactionEntityEventToFinancialTransactionEvent(FinancialTransactionEventEntity financialTransactionEventEntity);

    @InheritConfiguration
    @Mapping(source = "accountHolderId", target = "sender.id")
    @Mapping(source = "beneficiaryId", target = "beneficiary.id")
    FinancialTransactionEventEntity transactionDTORequestToFinancialTransactionEvent(TransactionDTORequest transactionDTORequest);

    @InheritConfiguration
    @Mapping(source = "sender.id", target = "accountHolderId")
    @Mapping(source = "beneficiary.id", target = "beneficiaryId")
    TransactionDTOResponse financialTransactionEntityEventToTransactioDTOResponse(FinancialTransactionEventEntity financialTransactionEventEntity);
}
