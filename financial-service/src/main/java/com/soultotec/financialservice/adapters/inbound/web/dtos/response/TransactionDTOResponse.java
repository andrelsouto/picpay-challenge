package com.soultotec.financialservice.adapters.inbound.web.dtos.response;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionType;

import java.math.BigDecimal;

public record TransactionDTOResponse(String accountHolderId,
                                     String beneficiaryId,
                                     TransactionType type,
                                     BigDecimal amount) {
}
