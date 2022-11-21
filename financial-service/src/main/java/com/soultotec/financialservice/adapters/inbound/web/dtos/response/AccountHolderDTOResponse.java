package com.soultotec.financialservice.adapters.inbound.web.dtos.response;

import lombok.Builder;

@Builder
public record AccountHolderDTOResponse(String id,
                                       String name,
                                       String documentNumber,
                                       WalletDTOResponse wallet) {
}
