package com.soultotec.transactionservice.adapters.inbound.jms;

import com.soultotec.transactionservice.adapters.config.JmsConfiguration;
import com.soultotec.transactionservice.adapters.inbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.transactionservice.application.entities.TransactionEntity;
import com.soultotec.transactionservice.application.mappers.TransactionMapper;
import com.soultotec.transactionservice.application.ports.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionListener {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionListener(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @JmsListener(destination = JmsConfiguration.TRANSACTION_QUEUE)
    public void listen(FinancialTransactionEvent event) {

        log.debug("Catching transaction for sender {} and beneficiary {}",
                event.sender().name(), event.beneficiary().name());
        TransactionEntity transaction = this.transactionMapper.financialTransactionEventToTransactionEntity(event);
        this.transactionService.saveTransaction(transaction);
    }
}
