package com.soultotec.financialservice.adapters.inbound.web.dtos.request;

import com.soultotec.financialservice.adapters.inbound.web.dtos.WalletTypeDTO;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record WalletDTORequest(BigDecimal amount, @NotNull WalletTypeDTO type) {
}
