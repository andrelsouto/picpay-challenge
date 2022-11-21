package com.soultotec.transactionservice.adapters.outbound.clients;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletClient(String type, BigDecimal amount) {
}
