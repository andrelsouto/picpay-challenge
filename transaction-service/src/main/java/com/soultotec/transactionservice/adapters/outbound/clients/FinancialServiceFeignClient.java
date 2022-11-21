package com.soultotec.transactionservice.adapters.outbound.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "financial-service", url = "${financial.service.url}")
public interface FinancialServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{accountHolderId}")
    AccountHolderClient getAccountHolderById(@PathVariable String accountHolderId);
}
