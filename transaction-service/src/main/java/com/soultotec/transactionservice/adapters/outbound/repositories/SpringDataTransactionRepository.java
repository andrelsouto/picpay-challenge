package com.soultotec.transactionservice.adapters.outbound.repositories;

import com.soultotec.transactionservice.adapters.outbound.repositories.domains.TransactionDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SpringDataTransactionRepository extends MongoRepository<TransactionDomain, String> {

    @Query("{'accountHolder.accountHolderId':  ?0}")
    List<TransactionDomain> findAllByAccountHolderId(String accountHolderId);
}
