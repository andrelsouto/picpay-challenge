package com.soultotec.financialservice.unit.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@ExtendWith(WireMockExtension.class)
@SpringBootTest
class FinancialServiceSampleTest {

    @Autowired
    WireMockServer wireMockServer;

    @TestConfiguration
    static class RestTemplateBuilderProvider {

        @Bean(destroyMethod = "stop")
        public WireMockServer wireMockServer() {
            WireMockServer server = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8090));
            server.start();
            return server;
        }
    }

}