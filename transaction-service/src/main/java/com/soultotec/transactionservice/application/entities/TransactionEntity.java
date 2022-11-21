package com.soultotec.transactionservice.application.entities;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionEntity(String id,
                                AccountHolderEntity accountHolder,
                                AccountHolderEntity beneficiary,
                                TransactionType type,
                                BigDecimal transactionAmount,
                                BigDecimal newBalance) {
}
