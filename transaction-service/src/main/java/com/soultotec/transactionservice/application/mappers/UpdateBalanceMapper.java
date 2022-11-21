package com.soultotec.transactionservice.application.mappers;

import com.soultotec.transactionservice.adapters.outbound.jms.domain.UpdateBalanceEvent;
import com.soultotec.transactionservice.application.entities.UpdateBalanceEventEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UpdateBalanceMapper {

    UpdateBalanceEvent updateBalanceEntityToUpdateBalanceEvent(UpdateBalanceEventEntity updateBalanceEvent);
}
