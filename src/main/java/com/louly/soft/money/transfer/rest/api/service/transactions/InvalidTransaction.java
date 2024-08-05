/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.transactions;

import com.louly.soft.money.transfer.rest.api.service.accounts.AbstractAccount;

import java.math.BigDecimal;

final class InvalidTransaction extends MoneyTransactionServiceImpl {

    private InvalidTransaction() {
        super(INVALID_ID, AbstractAccount.getInvalid(), AbstractAccount.getInvalid(), BigDecimal.ZERO);
    }

    @Override
    public int hashCode() {
        return (int) INVALID_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return (obj instanceof InvalidTransaction);
    }

    @Override
    public boolean run() {
        return false;
    }

    private static class LazyHolder {
        private static final InvalidTransaction INSTANCE = new InvalidTransaction();
    }

    static InvalidTransaction getInstance() {
        return LazyHolder.INSTANCE;
    }
}
