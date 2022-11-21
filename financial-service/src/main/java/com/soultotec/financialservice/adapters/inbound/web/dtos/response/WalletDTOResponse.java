package com.soultotec.financialservice.adapters.inbound.web.dtos.response;

import com.soultotec.financialservice.adapters.inbound.web.dtos.WalletTypeDTO;

import java.math.BigDecimal;

public record WalletDTOResponse(BigDecimal amount, WalletTypeDTO type) {
}
