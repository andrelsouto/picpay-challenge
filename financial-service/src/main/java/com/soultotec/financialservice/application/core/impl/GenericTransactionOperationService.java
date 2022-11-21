package com.soultotec.financialservice.application.core.impl;

import com.mongodb.client.result.UpdateResult;
import com.soultotec.financialservice.application.entities.AccountHolderEventEntity;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public sealed class GenericTransactionOperationService permits DepositTransactionOperationServiceImpl, PaymentTransactionOperationServiceImpl, WireTransferTransactionOperationImpl, WithdrawTransactionOperationServiceImpl {

    private final AccountHolderRepository accountHolderRepository;
    private final AccountHolderMapper accountHolderMapper;

    public GenericTransactionOperationService(AccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountHolderMapper = accountHolderMapper;
    }

    public Optional<AccountHolderEventEntity> getAccountHolder(String id) {

        return this.accountHolderRepository.getById(id)
                .map(this.accountHolderMapper::accountHolderToAccountHolderEventEntity);
    }

    protected void debitAmount(FinancialTransactionEventEntity financialTransactionEvent) {
        this.getAccountHolder(financialTransactionEvent.getSender().id())
                .ifPresent(financialTransactionEvent::setSender);

        if (this.isBalanceInvalid(financialTransactionEvent.getSender().wallet().amount(),
                financialTransactionEvent.getAmount())) {
            throw new InvalidBalanceException("Balance Invalid for this operation");
        }

        BigDecimal newBalance = financialTransactionEvent.getSender().wallet().amount().subtract(financialTransactionEvent.getAmount());
        this.updateBalance(financialTransactionEvent.getSender().id(),
                newBalance);
        financialTransactionEvent.setNewBalance(newBalance);
    }

    private UpdateResult updateBalance(String accountHolderId, BigDecimal newBalance) {

        return this.accountHolderRepository.updateWalletAmount(accountHolderId, newBalance);
    }

    public Boolean isBalanceInvalid(BigDecimal balance, BigDecimal debit) {

        return Objects.isNull(balance) || debit.compareTo(balance) > 0;
    }

    public AccountHolderRepository getAccountHolderRepository() {
        return this.accountHolderRepository;
    }
}
