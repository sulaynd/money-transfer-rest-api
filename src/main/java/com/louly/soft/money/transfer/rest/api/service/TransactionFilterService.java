package com.louly.soft.money.transfer.rest.api.service;


import com.louly.soft.money.transfer.rest.api.dao.filter.TransactionsFilter;
import com.louly.soft.money.transfer.rest.api.model.TransactionDto;
import com.louly.soft.money.transfer.rest.api.model.TransactionsPageResponse;

public interface TransactionFilterService {

    TransactionsPageResponse<TransactionDto> searchCars(TransactionsFilter filter, int page, int size);
}