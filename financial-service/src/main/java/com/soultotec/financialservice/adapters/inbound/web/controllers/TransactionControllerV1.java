package com.soultotec.financialservice.adapters.inbound.web.controllers;

import com.soultotec.financialservice.adapters.inbound.web.TransactionAdapterV1;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.TransactionDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransactionControllerV1 {

    private final TransactionAdapterV1 transactionAdapterV1;

    public TransactionControllerV1(TransactionAdapterV1 transactionAdapterV1) {
        this.transactionAdapterV1 = transactionAdapterV1;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDTOResponse> createTransaction(@RequestBody @Validated
                                                                    TransactionDTORequest transactionDTORequest) {

        return ResponseEntity.ok().body(this.transactionAdapterV1.createTransaction(transactionDTORequest));
    }
}
