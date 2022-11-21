package com.soultotec.financialservice.adapters.outbound.jms;

import com.soultotec.financialservice.adapters.config.JmsConfiguration;
import com.soultotec.financialservice.adapters.outbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;
import com.soultotec.financialservice.application.mappers.FinancialTransactionMapper;
import com.soultotec.financialservice.application.ports.jms.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventPublisherAdapterImpl implements TransactionEvent {

    private final JmsTemplate jmsTemplate;
    private final FinancialTransactionMapper financialTransactionMapper;

    public TransactionEventPublisherAdapterImpl(JmsTemplate jmsTemplate, FinancialTransactionMapper financialTransactionMapper) {
        this.jmsTemplate = jmsTemplate;
        this.financialTransactionMapper = financialTransactionMapper;
    }

    @Override
    public FinancialTransactionEventEntity sendMessage(FinancialTransactionEventEntity event) {

        log.info("Sending message for queue {} with id: {}", JmsConfiguration.TRANSACTION_QUEUE, event.getType());
        FinancialTransactionEvent transactionEvent =
                this.financialTransactionEventEntityToFinancialTransactionEvent(event);
        jmsTemplate.convertAndSend(JmsConfiguration.TRANSACTION_QUEUE, transactionEvent);
        return event;
    }

    private FinancialTransactionEvent financialTransactionEventEntityToFinancialTransactionEvent(
            FinancialTransactionEventEntity eventEntity) {

        return this.financialTransactionMapper.financialTransactionEntityEventToFinancialTransactionEvent(eventEntity);
    }
}
