package com.soultotec.transactionservice.adapters.inbound.web.domains;

import java.util.List;

public record TransactionDTOResponse(String id,
                                     AccountHolderDTOResponse accountHolder,
                                     List<TransactionTimelineDTOResponse> transactions) {
}
