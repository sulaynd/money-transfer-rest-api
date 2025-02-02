/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public final class TransactionPayload {

    private final Long debitAccountId;
    private final Long creditAccountId;
    private final BigDecimal amount;
}
