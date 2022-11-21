package com.soultotec.financialservice.adapters.outbound.repositories.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletDomain(WalletType type, BigDecimal amount) {
}
