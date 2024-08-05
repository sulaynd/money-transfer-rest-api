package com.louly.soft.money.transfer.rest.api.service.transactions;


import com.louly.soft.money.transfer.rest.api.consts.Consts;
import com.louly.soft.money.transfer.rest.api.enums.TransactionState;
import com.louly.soft.money.transfer.rest.api.service.accounts.AccountService;
import com.louly.soft.money.transfer.rest.api.utils.validators.Validator;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

// TODO Add DateCreated and LastModifiedDate
@Slf4j
public class MoneyTransactionServiceImpl implements TransactionService {

    private final Long id;
    private final AccountService debit;
    private final AccountService credit;
    private final BigDecimal amount;
    private TransactionState state;

    MoneyTransactionServiceImpl(Long id, AccountService debit, AccountService credit, BigDecimal amount) {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(debit, "Debit account cannot be null");
        Objects.requireNonNull(credit, "Credit account cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Validator.validateAmountPositive(amount);
        Validator.validateAccountsAreValid(debit, credit);
        Validator.validateAccountIsDifferent(debit, credit);
        // TODO Support multi-currency operations
        Validator.validateCurrencyIsTheSame(debit, credit);

        this.id = id;
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
        this.state = TransactionState.NEW;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public AccountService getDebit() {
        return debit;
    }

    @Override
    public AccountService getCredit() {
        return credit;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public synchronized TransactionState getState() {
        return state;
    }

    @Override
    public synchronized boolean run() {
        if (state != TransactionState.COMPLETED) {
            changeState();
            return doRun();
        }
        return false;
    }

    private boolean doRun() {
        final Lock debitLock = debit.writeLock();
        try {
            if (debitLock.tryLock(Consts.ACCOUNT_WAIT_INTERVAL, TimeUnit.MILLISECONDS)) {
                try {
                    final Lock creditLock = credit.writeLock();
                    if (creditLock.tryLock(Consts.ACCOUNT_WAIT_INTERVAL, TimeUnit.MILLISECONDS)) {
                        try {
                            if (debit.debit(amount)) {
                                if (credit.credit(amount)) {
                                    state = TransactionState.COMPLETED;
                                    log.trace("Transaction {} completed", id);
                                    return true;
                                }
                            }
                            state = TransactionState.INSUFFICIENT_FUNDS;
                        } finally {
                            creditLock.unlock();
                        }
                    } else {
                        state = TransactionState.CONCURRENCY_ERROR;
                    }
                } finally {
                    debitLock.unlock();
                }
            } else {
                state = TransactionState.CONCURRENCY_ERROR;
            }
        } catch (InterruptedException e) {
            state = TransactionState.CONCURRENCY_ERROR;
            log.error("Error occurred inside transaction", e);
            Thread.currentThread().interrupt();
        }
        return false;
    }

    private void changeState() {
        switch (state) {
            case INSUFFICIENT_FUNDS:
            case CONCURRENCY_ERROR:
                state = TransactionState.RESTARTED;
                break;
            default:
                // nothing to do
                break;
        }
    }

    public static TransactionService getInvalid() {
        return InvalidTransaction.getInstance();
    }

    public static TransactionService make(Long id, AccountService debit, AccountService credit, BigDecimal amount) {
        return new MoneyTransactionServiceImpl(id, debit, credit, amount);
    }
}
