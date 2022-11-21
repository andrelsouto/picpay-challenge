package com.soultotec.financialservice.adapters.inbound.jms.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateBalanceEvent(String accountHolderId, BigDecimal amount) {
}
