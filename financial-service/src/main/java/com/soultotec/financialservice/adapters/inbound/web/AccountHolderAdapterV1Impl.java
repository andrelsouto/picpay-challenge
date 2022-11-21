package com.soultotec.financialservice.adapters.inbound.web;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.AccountHolderDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.AccountHolderDTOResponse;
import com.soultotec.financialservice.application.entities.AccountHolder;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.services.FinancialService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountHolderAdapterV1Impl implements AccountHolderAdapterV1 {

    private final FinancialService financialService;
    private final AccountHolderMapper accountHolderMapper;

    public AccountHolderAdapterV1Impl(FinancialService financialService, AccountHolderMapper accountHolderMapper) {

        this.financialService = financialService;
        this.accountHolderMapper = accountHolderMapper;
    }

    @Override
    public AccountHolderDTOResponse saveUserAccount(AccountHolderDTORequest accountHolderDTORequest) {

        AccountHolder accountHolder = this.accountHolderMapper.accountHolderRequestToAccountHolder(accountHolderDTORequest);
        AccountHolder accountHolderSaved = this.financialService.saveAccountHolder(accountHolder);
        return this.accountHolderMapper.accountHolderDomainToAccountHolderDTOResponse(accountHolderSaved);
    }

    @Override
    public List<AccountHolderDTOResponse> listAccounts() {

        List<AccountHolder> accountHolderList = this.financialService.getAllAccountHolder();
        return accountHolderList.stream()
                .map(this.accountHolderMapper::accountHolderDomainToAccountHolderDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountHolderDTOResponse getUserAccountById(String id) {

        AccountHolder accountHolder = this.financialService.getAccountHolderById(id);
        return this.accountHolderMapper.accountHolderDomainToAccountHolderDTOResponse(accountHolder);
    }
}
