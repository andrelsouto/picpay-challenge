package com.soultotec.transactionservice.adapters.inbound.web.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionTimelineDTOResponse(BigDecimal amount,
                                             String type,
                                             BigDecimal newBalance,
                                             LocalDateTime transactionDateTime) {
}
