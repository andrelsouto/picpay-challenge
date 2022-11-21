package com.soultotec.financialservice.adapters.outbound.jms.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FinancialTransactionEvent(String id,
                                        AccountHolderEvent sender,
                                        AccountHolderEvent beneficiary,
                                        TransactionTypeEvent type,
                                        BigDecimal amount,
                                        BigDecimal newBalance) {
}
