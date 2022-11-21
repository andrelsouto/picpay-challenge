package com.soultotec.financialservice.adapters.outbound.jms.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record WalletEvent(UUID accountId, WalletTypeEvent type, BigDecimal amount) {

}
