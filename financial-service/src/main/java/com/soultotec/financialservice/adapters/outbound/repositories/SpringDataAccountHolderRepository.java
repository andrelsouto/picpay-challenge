package com.soultotec.financialservice.adapters.outbound.repositories;

import com.soultotec.financialservice.adapters.outbound.repositories.domain.AccountHolderDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAccountHolderRepository extends MongoRepository<AccountHolderDomain, String> {
}
