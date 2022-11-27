package com.soultotec.transactionservice.integrated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.soultotec.transactionservice.TransactionServiceApplication;
import com.soultotec.transactionservice.adapters.config.JmsConfiguration;
import com.soultotec.transactionservice.adapters.inbound.jms.domain.AccountHolderEvent;
import com.soultotec.transactionservice.adapters.inbound.jms.domain.FinancialTransactionEvent;
import com.soultotec.transactionservice.adapters.inbound.jms.domain.TransactionTypeEvent;
import com.soultotec.transactionservice.adapters.outbound.clients.AccountHolderClient;
import com.soultotec.transactionservice.adapters.outbound.clients.WalletClient;
import com.soultotec.transactionservice.adapters.outbound.repositories.SpringDataTransactionRepository;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionDomain;
import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionTypeDomain;
import com.soultotec.transactionservice.config.WireMockConfig;
import lombok.SneakyThrows;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(WireMockExtension.class)
@Import(WireMockConfig.class)
@SpringBootTest(classes = TransactionServiceApplication.class)
class TransactionListenerITest {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    SpringDataTransactionRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    FinancialTransactionEvent messageToSend;

    @BeforeEach
    public void setUp() {
        this.repository.deleteAll();
    }

    private FinancialTransactionEvent getMessageToSend(TransactionTypeEvent type) {

        return messageToSend = FinancialTransactionEvent.builder()
                .amount(BigDecimal.TEN)
                .sender(AccountHolderEvent.builder()
                        .id("123")
                        .documentNumber("123")
                        .name("test")
                        .build())
                .beneficiary(AccountHolderEvent.builder()
                        .id("321")
                        .documentNumber("321")
                        .name("test-beneficiary")
                        .build())
                .newBalance(BigDecimal.TEN)
                .type(type)
                .build();
    }

    @Test
    @SneakyThrows
    void shouldSaveWireTransferTransaction() {

        this.jmsTemplate.convertAndSend(JmsConfiguration.TRANSACTION_QUEUE, this.getMessageToSend(TransactionTypeEvent.WIRE_TRANSFER));

        this.wireMockServer.stubFor(WireMock.get("/api/v1/account/321")
                .willReturn(WireMock.okJson(this.objectMapper.writeValueAsString(this.getReponse()))));

        Awaitility.await().untilAsserted(() -> {

            List<TransactionDomain> beneficiaryTransactions = this.repository.findAllByAccountHolderId("321");
            List<TransactionDomain> senderTransactions = this.repository.findAllByAccountHolderId("123");

            assertEquals(1, beneficiaryTransactions.size());
            assertEquals(1, senderTransactions.size());
            assertEquals(TransactionTypeDomain.WIRE_TRANSFER, beneficiaryTransactions.get(0).type());
        });
    }

    @Test
    @SneakyThrows
    void shouldSaveDepositTransaction() {

        this.jmsTemplate.convertAndSend(JmsConfiguration.TRANSACTION_QUEUE, this.getMessageToSend(TransactionTypeEvent.DEPOSIT));

        Awaitility.await().untilAsserted(() -> {

            List<TransactionDomain> beneficiaryTransactions = this.repository.findAllByAccountHolderId("123");

            assertEquals(1, beneficiaryTransactions.size());
            assertEquals(TransactionTypeDomain.DEPOSIT, beneficiaryTransactions.get(0).type());
        });
    }

    @Test
    @SneakyThrows
    void shouldSavePaymentTransaction() {

        this.jmsTemplate.convertAndSend(JmsConfiguration.TRANSACTION_QUEUE, this.getMessageToSend(TransactionTypeEvent.PAYMENT));

        Awaitility.await().untilAsserted(() -> {

            List<TransactionDomain> beneficiaryTransactions = this.repository.findAllByAccountHolderId("123");

            assertEquals(1, beneficiaryTransactions.size());
            assertEquals(TransactionTypeDomain.PAYMENT, beneficiaryTransactions.get(0).type());
        });
    }

    @Test
    @SneakyThrows
    void shouldSaveWithdrawTransaction() {

        this.jmsTemplate.convertAndSend(JmsConfiguration.TRANSACTION_QUEUE, this.getMessageToSend(TransactionTypeEvent.WITHDRAW));

        Awaitility.await().untilAsserted(() -> {

            List<TransactionDomain> beneficiaryTransactions = this.repository.findAllByAccountHolderId("123");

            assertEquals(1, beneficiaryTransactions.size());
            assertEquals(TransactionTypeDomain.WITHDRAW, beneficiaryTransactions.get(0).type());
        });
    }

    private AccountHolderClient getReponse() {

        return AccountHolderClient.builder()
                .documentNumber("321")
                .id("321")
                .name("test-beneficiary")
                .wallet(WalletClient.builder()
                        .amount(BigDecimal.TEN)
                        .type("SAVINGS")
                        .build())
                .build();
    }
}