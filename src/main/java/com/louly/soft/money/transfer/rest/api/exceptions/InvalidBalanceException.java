package com.louly.soft.money.transfer.rest.api.exceptions;

import java.math.BigDecimal;

public class InvalidBalanceException extends RuntimeException {

    public InvalidBalanceException(BigDecimal expected, BigDecimal actual) {
        super(String.format("Invalid balance: expected %s, but actual was %s", expected, actual));
    }
}
