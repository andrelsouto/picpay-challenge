package com.soultotec.financialservice.adapters.inbound.web;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.TransactionDTOResponse;

public interface TransactionAdapterV1 {

    TransactionDTOResponse createTransaction(TransactionDTORequest transactionDTORequest);
}
