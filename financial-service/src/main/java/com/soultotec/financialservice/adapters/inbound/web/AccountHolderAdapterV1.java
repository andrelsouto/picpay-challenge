package com.soultotec.financialservice.adapters.inbound.web;

import com.soultotec.financialservice.adapters.inbound.web.dtos.request.AccountHolderDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.AccountHolderDTOResponse;

import java.util.List;

public interface AccountHolderAdapterV1 {

    AccountHolderDTOResponse saveUserAccount(AccountHolderDTORequest accountHolderDTORequest);

    List<AccountHolderDTOResponse> listAccounts();

    AccountHolderDTOResponse getUserAccountById(String id);

}
