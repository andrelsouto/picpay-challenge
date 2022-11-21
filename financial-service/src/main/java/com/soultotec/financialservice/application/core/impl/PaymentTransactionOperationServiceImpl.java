package com.soultotec.financialservice.application.core.impl;

import com.soultotec.financialservice.application.core.TransactionOperationService;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.entities.TransactionType;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public non-sealed class PaymentTransactionOperationServiceImpl extends GenericTransactionOperationService implements TransactionOperationService {

    private static final TransactionType OPERATION = TransactionType.PAYMENT;

    public PaymentTransactionOperationServiceImpl(AccountHolderRepository accountHolderRepository,
                                                  AccountHolderMapper accountHolderMapper) {
        super(accountHolderRepository, accountHolderMapper);
    }

    @Override
    public TransactionType type() {
        return PaymentTransactionOperationServiceImpl.OPERATION;
    }

    @Override
    @Transactional
    public FinancialTransactionEventEntity operateTransaction(FinancialTransactionEventEntity financialTransactionEvent) {
        log.info("Payment operation...");

        this.debitAmount(financialTransactionEvent);
        return financialTransactionEvent;
    }
}
