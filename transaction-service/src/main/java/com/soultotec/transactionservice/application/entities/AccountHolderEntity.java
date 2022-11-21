package com.soultotec.transactionservice.application.entities;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountHolderEntity(String id, String name, String documentNumber, BigDecimal walletBalance) {
}
