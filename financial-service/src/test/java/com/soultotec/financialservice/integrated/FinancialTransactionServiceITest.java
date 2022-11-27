package com.soultotec.financialservice.integrated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soultotec.financialservice.FinancialServiceApplication;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.TransactionType;
import com.soultotec.financialservice.adapters.outbound.repositories.SpringDataAccountHolderRepository;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.AccountHolderDomain;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.WalletDomain;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.WalletType;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(classes = FinancialServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FinancialTransactionServiceITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SpringDataAccountHolderRepository accountHolderRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateWireTransferTransaction() throws Exception {


        this.saveAccountHolder();

        List<AccountHolderDomain> accounts = this.accountHolderRepository.findAll();

        TransactionDTORequest dtoRequest = TransactionDTORequest.builder()
                .accountHolderId(accounts.get(0).id())
                .beneficiaryId(accounts.get(1).id())
                .type(TransactionType.WIRE_TRANSFER)
                .amount(new BigDecimal(100))
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoRequest))).andExpect(MockMvcResultMatchers.status().isOk());

        Awaitility.await().untilAsserted(() -> {

            this.accountHolderRepository.findById(accounts.get(0).id()).ifPresent(
                    updated -> assertEquals(new BigDecimal(900), updated.wallet().amount()));

            this.accountHolderRepository.findById(accounts.get(1).id()).ifPresent(
                    updated -> assertEquals(new BigDecimal(1100), updated.wallet().amount()));
        });
    }

    @Test
    void shouldCreateDepositTransaction() throws Exception {

        AccountHolderDomain saved = this.accountHolderRepository.save(AccountHolderDomain.builder()
                .name("test-sender")
                .documentNumber("124")
                .wallet(WalletDomain.builder()
                        .amount(new BigDecimal(1000))
                        .type(WalletType.SAVINGS)
                        .build())
                .build());

        TransactionDTORequest dtoRequest = TransactionDTORequest.builder()
                .accountHolderId(saved.id())
                .type(TransactionType.DEPOSIT)
                .amount(new BigDecimal(2500))
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoRequest))).andExpect(MockMvcResultMatchers.status().isOk());

        Awaitility.await().untilAsserted(() -> this.accountHolderRepository.findById(saved.id()).ifPresent(
                updated -> assertEquals(new BigDecimal(3500), updated.wallet().amount())));
    }

    private void saveAccountHolder() {
        AccountHolderDomain sender = AccountHolderDomain.builder()
                .name("test-sender")
                .documentNumber("123")
                .wallet(WalletDomain.builder()
                        .amount(new BigDecimal(1000))
                        .type(WalletType.SAVINGS)
                        .build())
                .build();
        AccountHolderDomain beneficiary = AccountHolderDomain.builder()
                .name("test-beneficiary")
                .documentNumber("321")
                .wallet(WalletDomain.builder()
                        .amount(new BigDecimal(1000))
                        .type(WalletType.SAVINGS)
                        .build())
                .build();

        this.accountHolderRepository.saveAll(List.of(sender, beneficiary));
    }
}
