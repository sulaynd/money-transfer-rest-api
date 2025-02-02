/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.utils.generators;

import io.github.mfvanek.money.transfer.interfaces.Account;
import io.github.mfvanek.money.transfer.interfaces.Party;
import io.github.mfvanek.money.transfer.interfaces.repositories.AccountsRepository;
import io.github.mfvanek.money.transfer.repositories.Context;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
class RandomAccountGenerator extends AbstractGenerator {

    private final int accountsPerClient;
    private final List<Long> partyIds;

    RandomAccountGenerator(final Context context, final List<Long> partyIds, final int accountsPerClient) {
        super(context, "accounts");
        Objects.requireNonNull(partyIds, "Ids list cannot be null");
        // TODO validate accountsPerClient
        this.partyIds = partyIds;
        this.accountsPerClient = accountsPerClient;
    }

    @Override
    List<Future<?>> doGenerate(final ExecutorService threadPool) {
        final List<Future<?>> futures = new ArrayList<>(partyIds.size() * accountsPerClient);
        for (Long partyId : partyIds) {
            Runnable runnableTask = () -> this.generateAccount(partyId);
            futures.add(threadPool.submit(runnableTask));
        }
        return futures;
    }

    private void generateAccount(Long partyId) {
        final AccountsRepository accountsRepository = context.getAccountsRepository();
        final Party pt = context.getPartyRepository().getById(partyId);
        if (pt.isValid()) {
            for (int i = 0; i < accountsPerClient; ++i) {
                final int idx = counter.incrementAndGet();
                final Account a = accountsRepository.addPassiveAccount(generateNumber(idx), pt);
                ids.add(a.getId());
            }
        } else {
            log.error("Party with id = {} not found", partyId);
        }
    }

    private static String generateNumber(final int idx) {
        return "4080281010" + StringUtils.leftPad(String.valueOf(idx), 10, '0');
    }
}
