package com.soultotec.transactionservice.application.entities;

import lombok.Builder;

import java.util.List;

@Builder
public record FinancialStatementEntity(String id,
                                       AccountHolderEntity accountHolder,
                                       List<TransactionTimelineEntity> transactions) {
}
