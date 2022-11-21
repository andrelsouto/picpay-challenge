package com.soultotec.financialservice.adapters.outbound.repositories.domain;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Builder
public record AccountHolderDomain(@Id String id,
                                  String name,
                                  String documentNumber,
                                  WalletDomain wallet,
                                  @CreatedDate Instant createdDate,
                                  @LastModifiedDate Instant lastModifiedDate) {
}
