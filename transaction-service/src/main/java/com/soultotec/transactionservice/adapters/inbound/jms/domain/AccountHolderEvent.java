package com.soultotec.transactionservice.adapters.inbound.jms.domain;

import lombok.Builder;

@Builder
public record AccountHolderEvent(String id,
                                 String name,
                                 String documentNumber) {
}
