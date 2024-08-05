package com.louly.soft.money.transfer.rest.api.service.transactions;


import com.louly.soft.money.transfer.rest.api.dao.interfaces.Identifiable;
import com.louly.soft.money.transfer.rest.api.enums.TransactionState;
import com.louly.soft.money.transfer.rest.api.service.accounts.AccountService;

import java.math.BigDecimal;

public interface TransactionService extends Identifiable {

    AccountService getDebit();

    AccountService getCredit();

    BigDecimal getAmount();

    TransactionState getState();

    boolean run();
}
