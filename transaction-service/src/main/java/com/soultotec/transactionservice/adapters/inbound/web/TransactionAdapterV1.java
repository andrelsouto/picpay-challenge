package com.soultotec.transactionservice.adapters.inbound.web;

import com.soultotec.transactionservice.adapters.inbound.web.domains.TransactionDTOResponse;

public interface TransactionAdapterV1 {

    TransactionDTOResponse getTransactionTimeLine(String accountHolderId);
}
