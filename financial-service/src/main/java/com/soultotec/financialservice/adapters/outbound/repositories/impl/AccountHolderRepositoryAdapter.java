package com.soultotec.financialservice.adapters.outbound.repositories.impl;

import com.mongodb.client.result.UpdateResult;
import com.soultotec.financialservice.adapters.outbound.repositories.SpringDataAccountHolderRepository;
import com.soultotec.financialservice.adapters.outbound.repositories.domain.AccountHolderDomain;
import com.soultotec.financialservice.application.entities.AccountHolder;
import com.soultotec.financialservice.application.mappers.AccountHolderMapper;
import com.soultotec.financialservice.application.ports.repositories.AccountHolderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountHolderRepositoryAdapter implements AccountHolderRepository {

    private final SpringDataAccountHolderRepository accountHolderRepository;
    private final AccountHolderMapper accountHolderMapper;
    private final MongoTemplate template;

    public AccountHolderRepositoryAdapter(SpringDataAccountHolderRepository accountHolderRepository, AccountHolderMapper accountHolderMapper, MongoTemplate template) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountHolderMapper = accountHolderMapper;
        this.template = template;
    }

    @Override
    public AccountHolder save(AccountHolder accountHolder) {

        AccountHolderDomain accountHolderDomain = this.accountHolderMapper.accountHolderToAccountHolderDomain(accountHolder);
        AccountHolderDomain domainSaved = this.accountHolderRepository.save(accountHolderDomain);
        return this.accountHolderMapper.accountHolderDomainToAccountHolder(domainSaved);
    }

    @Override
    public Optional<AccountHolder> getById(String id) {

        Optional<AccountHolderDomain> accountHolderDomainOptional = this.accountHolderRepository.findById(id);
        return accountHolderDomainOptional.map(this.accountHolderMapper::accountHolderDomainToAccountHolder);
    }

    @Override
    public List<AccountHolder> findAll() {
        return this.accountHolderRepository.findAll()
                .stream().map(this.accountHolderMapper::accountHolderDomainToAccountHolder)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateResult updateWalletAmount(String accountHolderId, BigDecimal amount) {

        return this.template.updateFirst(Query.query(
                Criteria.where("_id").is(accountHolderId)),
                Update.update("wallet.amount", amount), AccountHolderDomain.class);
    }
}
