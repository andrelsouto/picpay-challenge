package com.soultotec.transactionservice.adapters.inbound.web.domains;

public record AccountHolderDTOResponse(String accountHolderId,
                                       String name,
                                       String documentNumber) {
}
