package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.Currency;


import java.math.BigDecimal;
import java.util.Collection;

public interface AccountsRepository extends Repository<Account>, Cleanable {

    Account addOurBankAccount(Currency currency, String number, BigDecimal balance);

    Account addOurBankAccount(String number, BigDecimal balance);

    Account getOurBankMainAccount();

    Account addPassiveAccount(Currency currency, String number, Party holder);

    Account addPassiveAccount(String number, Party holder);

    BigDecimal getInitialBalance();

    void validateBalance();

    Collection<Account> getByHolder(Party holder);
}
