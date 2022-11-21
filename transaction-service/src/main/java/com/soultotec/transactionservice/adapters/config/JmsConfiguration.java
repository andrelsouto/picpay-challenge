package com.soultotec.transactionservice.adapters.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soultotec.transactionservice.adapters.inbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.transactionservice.adapters.outbound.jms.domain.UpdateBalanceEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfiguration {

    public static final String TRANSACTION_QUEUE = "financial-transaction-queue";
    public static final String UPDATE_BALANCE_QUEUE = "update-balance-queue";

    @Bean
    public MessageConverter jacksonJmsConverter(ObjectMapper objectMapper) {

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("JMS_TYPE", FinancialTransactionEvent.class);
        typeIdMappings.put("JMS_UPDATE_TYPE", UpdateBalanceEvent.class);
        converter.setTypeIdPropertyName("_type");
        converter.setTypeIdMappings(typeIdMappings);
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
