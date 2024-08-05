package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;


import java.math.BigDecimal;

public interface TransactionRepository extends Repository<Transaction>, Cleanable {

    Transaction add(Account debit, Account credit, BigDecimal amount);

    PagedResult<Transaction> getByAccount(Account account, int pageNumber, int recordsPerPage);

    default PagedResult<Transaction> getByAccount(Account account, Pagination pagination) {
        return getByAccount(account, pagination.getPageNumber(), pagination.getRecordsPerPage());
    }
}
