package com.soultotec.transactionservice.adapters.outbound.jms.domain;

import java.math.BigDecimal;

public record UpdateBalanceEvent(String accountHolderId, BigDecimal amount) {
}
