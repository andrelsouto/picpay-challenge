package com.soultotec.financialservice.application.ports.services;

import com.soultotec.financialservice.application.entities.AccountHolder;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface FinancialService {

    AccountHolder saveAccountHolder(AccountHolder accountHolder);

    AccountHolder getAccountHolderById(String id);

    List<AccountHolder> getAllAccountHolder();
}
