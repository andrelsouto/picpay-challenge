package com.soultotec.financialservice.integrated.components;

import com.soultotec.financialservice.adapters.config.JmsConfiguration;
import com.soultotec.financialservice.adapters.inbound.jms.domain.UpdateBalanceEvent;
import com.soultotec.financialservice.adapters.outbound.jms.domain.FinancialTransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionQueueListener {

    private final JmsTemplate jmsTemplate;

    public TransactionQueueListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfiguration.TRANSACTION_QUEUE)
    public void listen(FinancialTransactionEvent event) {

        log.info("Updating wallet balance");
        switch (event.type()) {
            case WIRE_TRANSFER -> {
                log.info(event.sender().name());
                log.info(event.beneficiary().name());
                jmsTemplate.convertAndSend(JmsConfiguration.OPERATION_QUEUE, UpdateBalanceEvent.builder()
                        .amount(event.amount())
                        .accountHolderId(event.beneficiary().id())
                        .build());
            }
            case DEPOSIT ->
                    jmsTemplate.convertAndSend(JmsConfiguration.OPERATION_QUEUE, UpdateBalanceEvent.builder()
                            .amount(event.amount())
                            .accountHolderId(event.sender().id())
                            .build());
        }
    }
}
