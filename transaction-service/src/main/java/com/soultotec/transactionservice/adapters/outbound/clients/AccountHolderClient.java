package com.soultotec.transactionservice.adapters.outbound.clients;

import lombok.Builder;

@Builder
public record AccountHolderClient(String id, String name, String documentNumber, WalletClient wallet) {
}
