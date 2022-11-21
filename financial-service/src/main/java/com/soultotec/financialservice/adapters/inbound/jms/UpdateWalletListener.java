package com.soultotec.financialservice.adapters.inbound.jms;

import com.soultotec.financialservice.adapters.config.JmsConfiguration;
import com.soultotec.financialservice.adapters.inbound.jms.domain.UpdateBalanceEvent;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateWalletListener {

    private final AccountHolderRepository accountHolderRepository;

    public UpdateWalletListener(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    @JmsListener(destination = JmsConfiguration.OPERATION_QUEUE)
    public void listen(UpdateBalanceEvent updateBalanceEvent) {

        log.info("Updating wallet balance");

        this.accountHolderRepository.getById(updateBalanceEvent.accountHolderId())
                .ifPresent(accountHolder ->
                    this.accountHolderRepository.updateWalletAmount(updateBalanceEvent.accountHolderId(),
                            accountHolder.wallet().amount().add(updateBalanceEvent.amount())));
    }
}
