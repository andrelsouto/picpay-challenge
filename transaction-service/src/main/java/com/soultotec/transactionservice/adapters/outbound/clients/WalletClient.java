package com.soultotec.transactionservice.adapters.outbound.clients;

import java.math.BigDecimal;

public record WalletClient(String type, BigDecimal amount) {
}
