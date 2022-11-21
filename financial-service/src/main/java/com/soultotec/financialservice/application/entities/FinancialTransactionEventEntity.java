package com.soultotec.financialservice.application.entities;

import java.math.BigDecimal;

public class FinancialTransactionEventEntity {

    private String id;
    private AccountHolderEventEntity sender;
    private AccountHolderEventEntity beneficiary;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal newBalance;

    public FinancialTransactionEventEntity() {
    }

    public FinancialTransactionEventEntity(String id, AccountHolderEventEntity sender, AccountHolderEventEntity beneficiary, TransactionType type, BigDecimal amount) {
        this.id = id;
        this.sender = sender;
        this.beneficiary = beneficiary;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountHolderEventEntity getSender() {
        return sender;
    }

    public void setSender(AccountHolderEventEntity sender) {
        this.sender = sender;
    }

    public AccountHolderEventEntity getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(AccountHolderEventEntity beneficiary) {
        this.beneficiary = beneficiary;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
}
