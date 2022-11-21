package com.soultotec.financialservice.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soultotec.financialservice.adapters.inbound.web.AccountHolderAdapterV1;
import com.soultotec.financialservice.adapters.inbound.web.controllers.AccountHolderControllerV1;
import com.soultotec.financialservice.adapters.inbound.web.dtos.WalletTypeDTO;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.AccountHolderDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.request.WalletDTORequest;
import com.soultotec.financialservice.adapters.inbound.web.dtos.response.AccountHolderDTOResponse;
import com.soultotec.financialservice.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;

@Import(TestConfig.class)
@WebMvcTest(AccountHolderControllerV1.class)
class AccountHolderControllerV1Test {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountHolderAdapterV1 accountHolderAdapter;

    @Test
    void getAccountHolderByIdTest() throws Exception {

        BDDMockito.given(this.accountHolderAdapter.getUserAccountById(ArgumentMatchers.anyString()))
                .willReturn(AccountHolderDTOResponse.builder().build());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/12345").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void listAccountsTest() throws Exception {

        BDDMockito.given(this.accountHolderAdapter.listAccounts())
                .willReturn(Collections.singletonList(AccountHolderDTOResponse.builder().build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveUserAccount400ErrorTest() throws Exception {

        BDDMockito.given(this.accountHolderAdapter.saveUserAccount(ArgumentMatchers.any(AccountHolderDTORequest.class)))
                .willReturn(AccountHolderDTOResponse.builder().build());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void saveUserAccount201Test() throws Exception {

        BDDMockito.given(this.accountHolderAdapter.saveUserAccount(ArgumentMatchers.any(AccountHolderDTORequest.class)))
                .willReturn(AccountHolderDTOResponse.builder().build());

        AccountHolderDTORequest accountHolderDTORequest = AccountHolderDTORequest.builder()
                .name("Test")
                .documentNumber("1234")
                .wallet(WalletDTORequest.builder().amount(BigDecimal.TEN).type(WalletTypeDTO.SAVINGS).build())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(accountHolderDTORequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}