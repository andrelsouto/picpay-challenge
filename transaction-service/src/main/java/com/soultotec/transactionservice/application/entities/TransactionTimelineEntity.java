package com.soultotec.transactionservice.application.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionTimelineEntity(BigDecimal amount,
                                        String type,
                                        BigDecimal newBalance,
                                        LocalDateTime transactionDateTime) {
}
