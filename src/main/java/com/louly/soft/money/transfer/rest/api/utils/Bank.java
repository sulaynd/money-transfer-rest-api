/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.utils;


import com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories.Repository;

import com.louly.soft.money.transfer.rest.api.dao.repository.Context;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
public final class Bank {

    private final Context context;

    private Bank(Context context) {
        this.context = context;
    }



    public Transaction transfer(TransactionPayload payload) {
        Objects.requireNonNull(payload, "Transaction data cannot be null");

        final Repository<Account> accountRepository = context.getAccountsRepository();
        final Account debit = accountRepository.getById(payload.getDebitAccountId());
        validateAccount(debit, payload.getDebitAccountId());
        final Account credit = accountRepository.getById(payload.getCreditAccountId());
        validateAccount(credit, payload.getCreditAccountId());

        final Transaction trn = context.getTransactionRepository().add(debit, credit, payload.getAmount());
        trn.run();
        return trn;
    }

    private void validateAccount(Account account, Long id) {
        if (account.isNotValid()) {
            throw new NoSuchElementException(String.format("Account with id %d is not found", id));
        }
    }


}
