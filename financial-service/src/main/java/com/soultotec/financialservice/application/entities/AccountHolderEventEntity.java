package com.soultotec.financialservice.application.entities;

public record AccountHolderEventEntity(String id, String name, String documentNumber, WalletEventEntity wallet) {
}
