package com.soultotec.financialservice.adapters.inbound.web.dtos.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Builder
public record AccountHolderDTORequest(@NotEmpty String name,
                                      @NotEmpty String documentNumber,
                                      @Validated WalletDTORequest wallet) {
}
