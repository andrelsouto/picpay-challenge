package com.soultotec.transactionservice.adapters.inbound.jms.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FinancialTransactionEvent(AccountHolderEvent sender,
                                        AccountHolderEvent beneficiary,
                                        TransactionTypeEvent type,
                                        BigDecimal amount,
                                        BigDecimal newBalance) {
}
