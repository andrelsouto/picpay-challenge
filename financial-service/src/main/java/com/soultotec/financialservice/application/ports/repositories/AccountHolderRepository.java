package com.soultotec.financialservice.application.ports.repositories;

import com.mongodb.client.result.UpdateResult;
import com.soultotec.financialservice.application.entities.AccountHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountHolderRepository {

    AccountHolder save(AccountHolder accountHolder);

    Optional<AccountHolder> getById(String id);

    List<AccountHolder> findAll();
    UpdateResult updateWalletAmount(String accountHolderId, BigDecimal amount);
}
