package com.soultotec.financialservice.application.ports.jms;

import com.soultotec.financialservice.application.entities.FinancialTransactionEventEntity;

public interface TransactionEvent {

    FinancialTransactionEventEntity sendMessage(FinancialTransactionEventEntity event);
}
