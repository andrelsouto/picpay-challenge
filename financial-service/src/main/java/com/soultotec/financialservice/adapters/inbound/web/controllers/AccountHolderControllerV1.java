package com.soultotec.financialservice.adapters.inbound.web.controllers;

import com.soultotec.financialservice.adapters.inbound.web.AccountHolderAdapterV1;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.AccountHolderDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.AccountHolderDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountHolderControllerV1 {

    private final AccountHolderAdapterV1 accountHolderAdapter;

    public AccountHolderControllerV1(AccountHolderAdapterV1 accountHolderAdapter) {
        this.accountHolderAdapter = accountHolderAdapter;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountHolderDTOResponse> saveUserAccount(@RequestBody @Validated AccountHolderDTORequest accountHolderDTORequest) {

        AccountHolderDTOResponse accountHolderSaved = accountHolderAdapter.saveUserAccount(accountHolderDTORequest);
        return ResponseEntity.created(UriComponentsBuilder.fromUriString("/v1/api/account/{id}").build(accountHolderSaved.id()))
                .body(accountHolderSaved);
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountHolderDTOResponse>> listAccounts() {

        return ResponseEntity.ok(this.accountHolderAdapter.listAccounts());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountHolderDTOResponse> getUserAccountById(@PathVariable("id") String id) {

        return ResponseEntity.ok(this.accountHolderAdapter.getUserAccountById(id));
    }
}
