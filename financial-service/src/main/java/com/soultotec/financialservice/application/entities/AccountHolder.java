package com.soultotec.financialservice.application.entities;

import lombok.Builder;

@Builder
public record AccountHolder(String id, String name, String documentNumber, WalletEventEntity wallet) {
}
