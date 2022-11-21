package com.soultotec.transactionservice.application.ports.jms;

import com.soultotec.transactionservice.application.entities.UpdateBalanceEventEntity;

public interface UpdateFinancialBalanceEvent {

    void sendMessage(UpdateBalanceEventEntity updateBalanceEventEntity);
}
