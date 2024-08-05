package com.louly.soft.money.transfer.rest.api.service.accounts;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.Currency;
import com.louly.soft.money.transfer.rest.api.dao.interfaces.Identifiable;
import com.louly.soft.money.transfer.rest.api.service.parties.Party;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

public interface AccountService extends Identifiable {

    String getNumber();

    Currency getCurrency();

    BigDecimal getBalance();

    boolean debit(BigDecimal amount);

    boolean credit(BigDecimal amount);

    Party getHolder();

    boolean isActive();

    Lock writeLock();
}
