/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.dao.repository;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories.AccountsRepository;
import com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories.Cleanable;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories.PartyRepository;
import com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories.TransactionRepository;
import lombok.Getter;

@Getter
public class Context implements Cleanable {

    private final PartyRepository partyRepository;
    private final AccountsRepository accountsRepository;
    private final TransactionRepository transactionRepository;

    private Context(PartyRepository partyRepository,
                    AccountsRepository accountsRepository,
                    TransactionRepository transactionRepository) {
        this.partyRepository = partyRepository;
        this.accountsRepository = accountsRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void clear() {
        partyRepository.clear();
        accountsRepository.clear();
        transactionRepository.clear();
    }


}
