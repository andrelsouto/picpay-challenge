package com.soultotec.transactionservice.adapters.outbound.clients;

public record AccountHolderClient(String id, String name, String documentNumber, WalletClient wallet) {
}
