package com.soultotec.financialservice.application.core.impl;

import com.soultotec.financialservice.application.core.TransactionOperationService;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.entities.TransactionType;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Slf4j
public non-sealed class WireTransferTransactionOperationImpl extends GenericTransactionOperationService implements TransactionOperationService {

    private static final TransactionType OPERATION = TransactionType.WIRE_TRANSFER;


    public WireTransferTransactionOperationImpl(AccountHolderRepository accountHolderRepository,
                                                AccountHolderMapper accountHolderMapper) {
        super(accountHolderRepository, accountHolderMapper);
    }

    @Override
    public TransactionType type() {
        return WireTransferTransactionOperationImpl.OPERATION;
    }

    @Override
    @Transactional
    public FinancialTransactionEventEntity operateTransaction(FinancialTransactionEventEntity financialTransactionEvent) {

        log.info("Transferring money...");
         this.getAccountHolder(financialTransactionEvent.getSender().id())
                .ifPresent(financialTransactionEvent::setSender);

         if (this.isBalanceInvalid(financialTransactionEvent.getSender().wallet().amount(),
                 financialTransactionEvent.getAmount())) {
             throw new InvalidBalanceException("Balance Invalid for this operation.");
         }

         if(Objects.isNull(financialTransactionEvent.getBeneficiary()) ||
                 financialTransactionEvent.getBeneficiary().id().isEmpty()) {
             throw new InvalidBeneficiaryExcpetion("You must have to provide a beneficiary.");
         }

         this.getAccountHolder(financialTransactionEvent.getBeneficiary().id())
                .ifPresent(financialTransactionEvent::setBeneficiary);
        this.debitAmount(financialTransactionEvent);

         return financialTransactionEvent;
    }
}
