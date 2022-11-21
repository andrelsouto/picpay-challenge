package com.soultotec.transactionservice.adapters.inbound.web.controllers;

import com.soultotec.transactionservice.adapters.inbound.web.TransactionAdapterV1;
import com.soultotec.transactionservice.adapters.inbound.web.domains.TransactionDTOResponse;
import com.soultotec.transactionservice.application.entities.FinancialStatementEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionControllerV1 {

    private final TransactionAdapterV1 transactionAdapterV1;

    public TransactionControllerV1(TransactionAdapterV1 transactionAdapterV1) {
        this.transactionAdapterV1 = transactionAdapterV1;
    }

    @GetMapping("/account/{accountHolderId}")
    public ResponseEntity<TransactionDTOResponse> getTransactionTimeLine(@PathVariable("accountHolderId")
                                                                         String accountHolderId) {

        return ResponseEntity.ok(this.transactionAdapterV1.getTransactionTimeLine(accountHolderId));
    }
}
