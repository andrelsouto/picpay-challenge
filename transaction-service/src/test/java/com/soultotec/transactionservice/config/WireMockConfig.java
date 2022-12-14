package com.soultotec.transactionservice.config;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfig {

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer() {

        WireMockServer server = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        server.start();
        return server;
    }
}
