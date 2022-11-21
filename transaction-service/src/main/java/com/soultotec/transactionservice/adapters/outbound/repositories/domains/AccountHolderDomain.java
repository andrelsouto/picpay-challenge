package com.soultotec.transactionservice.adapters.outbound.repositories.domains;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountHolderDomain(String accountHolderId, String name, String documentNumber) {
}
