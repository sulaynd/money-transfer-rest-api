/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.currencies;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.Currency;
import com.louly.soft.money.transfer.rest.api.utils.validators.Validator;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ToString
@EqualsAndHashCode
public class BaseCurrency implements Currency {

    private static final ConcurrentMap<String, Currency> CURRENCIES = new ConcurrentHashMap<>();

    private final String isoCode;

    BaseCurrency(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public boolean isValid() {
        return isoCode.length() == ISO_CODE_LENGTH;
    }

    @Override
    public String getISOCode() {
        return isoCode;
    }

    public static Currency valueOf(String isoCode) {
        Objects.requireNonNull(isoCode, "Currency code cannot be null");
        Validator.validateCurrencyCode(isoCode);

        final String code = isoCode.toUpperCase(Locale.ROOT);
        Currency currency = CURRENCIES.get(code);
        if (currency == null) {
            currency = CURRENCIES.computeIfAbsent(code, (k) -> new BaseCurrency(code));
        }
        return currency;
    }

    public static Currency getInvalid() {
        return InvalidCurrency.getInstance();
    }

    public static Currency getDefault() {
        return valueOf("RUB");
    }
}
