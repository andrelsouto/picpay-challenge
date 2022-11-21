package com.soultotec.transactionservice.adapters.outbound.jms;

import com.soultotec.transactionservice.adapters.config.JmsConfiguration;
import com.soultotec.transactionservice.adapters.outbound.jms.domain.UpdateBalanceEvent;
import com.soultotec.transactionservice.application.entities.UpdateBalanceEventEntity;
import com.soultotec.transactionservice.application.mappers.UpdateBalanceMapper;
import com.soultotec.transactionservice.application.ports.jms.UpdateFinancialBalanceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateFinancialBalanceEventPublisherAdapterImpl implements UpdateFinancialBalanceEvent {

    private final JmsTemplate jmsTemplate;
    private final UpdateBalanceMapper updateBalanceMapper;

    public UpdateFinancialBalanceEventPublisherAdapterImpl(JmsTemplate jmsTemplate, UpdateBalanceMapper updateBalanceMapper) {
        this.jmsTemplate = jmsTemplate;
        this.updateBalanceMapper = updateBalanceMapper;
    }

    @Override
    public void sendMessage(UpdateBalanceEventEntity updateBalanceEventEntity) {

        log.info("Update the balance for Account: {}", updateBalanceEventEntity.accountHolderId());
        UpdateBalanceEvent updateBalanceEvent =
                this.updateBalanceMapper.updateBalanceEntityToUpdateBalanceEvent(updateBalanceEventEntity);
        this.jmsTemplate.convertAndSend(JmsConfiguration.UPDATE_BALANCE_QUEUE, updateBalanceEvent);
    }
}
