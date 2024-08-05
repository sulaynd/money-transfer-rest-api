/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.currencies;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.Currency;
import com.louly.soft.money.transfer.rest.api.dao.interfaces.Identifiable;


final class InvalidCurrency extends BaseCurrency {

    private InvalidCurrency() {
        super("");
    }

    @Override
    public int hashCode() {
        return (int) Identifiable.INVALID_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return (obj instanceof InvalidCurrency);
    }

    private static class LazyHolder {
        private static final InvalidCurrency INSTANCE = new InvalidCurrency();
    }

    static Currency getInstance() {
        return LazyHolder.INSTANCE;
    }
}
