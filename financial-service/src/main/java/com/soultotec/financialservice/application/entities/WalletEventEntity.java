package com.soultotec.financialservice.application.entities;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletEventEntity(AccountType type, BigDecimal amount) {
}
