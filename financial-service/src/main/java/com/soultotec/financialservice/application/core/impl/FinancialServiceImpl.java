package com.soultotec.financialservice.application.core.impl;

import com.soultotec.financialservice.application.entities.AccountHolder;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import com.soultotec.financialservice.application.ports.services.FinancialService;

import java.util.List;

public class FinancialServiceImpl implements FinancialService {

    private final AccountHolderRepository accountHolderRepository;

    public FinancialServiceImpl(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    @Override
    public AccountHolder saveAccountHolder(AccountHolder accountHolder) {
        return this.accountHolderRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolderById(String id) {
            return this.accountHolderRepository
                    .getById(id).orElseThrow(PageNotFoundException::new);
    }

    @Override
    public List<AccountHolder> getAllAccountHolder() {
        return this.accountHolderRepository
                .findAll();
    }
}
