package com.soultotec.financialservice.unit.services;

import com.soultotec.financialservice.application.core.impl.FinancialServiceImpl;
import com.soultotec.financialservice.application.entities.AccountHolder;
import com.soultotec.financialservice.application.entities.AccountType;
import com.soultotec.financialservice.application.entities.WalletEventEntity;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import com.soultotec.financialservice.application.ports.services.FinancialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FinancialServiceTest {


    FinancialService financialService;

    @Mock
    AccountHolderRepository accountHolderRepository;

    @BeforeEach
    void setUp() {
        this.financialService = new FinancialServiceImpl(accountHolderRepository);
    }

    @Test
    void saveAccountHolderTest() {

        BDDMockito.given(this.accountHolderRepository.save(ArgumentMatchers.any())).willReturn(AccountHolder.builder().build());

        AccountHolder accountHolderToSave = AccountHolder.builder()
                .name("Test")
                .documentNumber("123")
                .wallet(WalletEventEntity.builder().type(AccountType.SAVINGS).amount(BigDecimal.TEN).build())
                .build();
        this.financialService.saveAccountHolder(accountHolderToSave);
        Mockito.verify(this.accountHolderRepository, Mockito.times(1)).save(accountHolderToSave);
    }

    @Test
    void getAccountHolderByIdTest() {

        BDDMockito.given(this.accountHolderRepository.getById(ArgumentMatchers.anyString())).willReturn(Optional.ofNullable(AccountHolder.builder()
                .name("Test")
                .documentNumber("123")
                .wallet(WalletEventEntity.builder().type(AccountType.SAVINGS).amount(BigDecimal.TEN).build())
                .build()));

        AccountHolder accountHolderById = this.financialService.getAccountHolderById("123");

        assertEquals("Test", accountHolderById.name());
        assertEquals("123", accountHolderById.documentNumber());
        assertNotNull(accountHolderById.wallet());
        Mockito.verify(this.accountHolderRepository, Mockito.times(1)).getById("123");
    }
}