package com.soultotec.transactionservice.adapters.outbound.repositories.domains;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Document
public record TransactionDomain(@Id String id,
                                AccountHolderDomain accountHolder,
                                TransactionTypeDomain type,
                                BigDecimal amount,
                                BigDecimal newBalance,
                                @CreatedDate Instant createdDate,
                                @LastModifiedDate Instant lastModifiedDate) {
}
