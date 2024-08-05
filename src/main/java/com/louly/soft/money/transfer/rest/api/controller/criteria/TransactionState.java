package com.louly.soft.money.transfer.rest.api.controller.criteria;

public enum TransactionState {

    NEW,
    INSUFFICIENT_FUNDS,
    COMPLETED,
    CONCURRENCY_ERROR,
    RESTARTED
}
