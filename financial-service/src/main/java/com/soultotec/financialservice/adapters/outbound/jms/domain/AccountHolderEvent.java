package com.soultotec.financialservice.adapters.outbound.jms.domain;

import lombok.Builder;

@Builder
public record AccountHolderEvent(String id,
                                 String name,
                                 String documentNumber,
                                 WalletEvent wallet) {
}
