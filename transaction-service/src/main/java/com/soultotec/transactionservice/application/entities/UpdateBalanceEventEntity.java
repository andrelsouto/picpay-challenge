package com.soultotec.transactionservice.application.entities;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateBalanceEventEntity(String accountHolderId, BigDecimal amount) {
}
