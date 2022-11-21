package com.soultotec.transactionservice.adapters.inbound.jms.domain;

public record AccountHolderEvent(String id,
                                 String name,
                                 String documentNumber) {
}
