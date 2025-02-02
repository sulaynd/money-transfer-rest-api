/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.utils.generators;

import io.github.mfvanek.money.transfer.interfaces.Account;
import io.github.mfvanek.money.transfer.interfaces.Transaction;
import io.github.mfvanek.money.transfer.interfaces.repositories.AccountsRepository;
import io.github.mfvanek.money.transfer.repositories.Context;
import io.github.mfvanek.money.transfer.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
class InitialTransactionGenerator extends AbstractGenerator {

    private final List<Long> accountIds;
    private final boolean runImmediately;

    InitialTransactionGenerator(Context context, List<Long> accountIds, boolean runImmediately) {
        super(context, "initial transactions");
        Objects.requireNonNull(accountIds, "Ids list cannot be null");
        this.accountIds = accountIds;
        this.runImmediately = runImmediately;
    }

    @Override
    List<Future<?>> doGenerate(final ExecutorService threadPool) {
        final List<Future<?>> futures = new ArrayList<>(accountIds.size());
        for (Long accountId : accountIds) {
            Runnable runnableTask = () -> this.generateInitialTransaction(accountId);
            futures.add(threadPool.submit(runnableTask));
        }
        return futures;
    }

    private void generateInitialTransaction(final Long creditAccountId) {
        final AccountsRepository accountsRepository = context.getAccountsRepository();
        final Account debit = accountsRepository.getOurBankMainAccount();
        final Account credit = accountsRepository.getById(creditAccountId);
        if (credit.isValid()) {
            // TODO move to params
            final BigDecimal amount = TransactionUtils.generateAmount(500_000, 1000_000);
            final Transaction transaction = context.getTransactionRepository().add(debit, credit, amount);
            if (runImmediately) {
                transaction.run();
            }
            ids.add(transaction.getId());
        } else {
            log.error("Credit account with id = {} not found", creditAccountId);
        }
    }
}
