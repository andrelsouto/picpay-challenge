package com.soultotec.financialservice.adapters.inbound.web.dtos.request;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Builder
public record TransactionDTORequest(@NotEmpty String accountHolderId,
                                    String beneficiaryId,
                                    @NotNull TransactionType type,
                                    @NotNull @Positive BigDecimal amount) {
}
