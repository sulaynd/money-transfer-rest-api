/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.accounts;


import com.louly.soft.money.transfer.rest.api.service.currencies.BaseCurrency;

import java.math.BigDecimal;

final class InvalidAccount extends AbstractAccount {

    private InvalidAccount() {
        super(INVALID_ID, BaseCurrency.getInvalid(), "", AbstractParty.getInvalid(), false, BigDecimal.ZERO);
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

        return (obj instanceof InvalidAccount);
    }

    @Override
    public String toString() {
        final String base = super.toString();
        return base.replace("Account{", "InvalidAccount{");
    }

    private static class LazyHolder {
        private static final InvalidAccount INSTANCE = new InvalidAccount();
    }

    static Account getInstance() {
        return LazyHolder.INSTANCE;
    }
}
