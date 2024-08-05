package com.louly.soft.money.transfer.rest.api.service;


import com.louly.soft.money.transfer.rest.api.dao.Transaction;
import com.louly.soft.money.transfer.rest.api.dao.filter.TransactionsFilter;
import com.louly.soft.money.transfer.rest.api.dao.repository.TransactionRepository;
import com.louly.soft.money.transfer.rest.api.dao.specification.TransactionSpec;
import com.louly.soft.money.transfer.rest.api.mapper.TransactionMapper;
import com.louly.soft.money.transfer.rest.api.model.TransactionDto;
import com.louly.soft.money.transfer.rest.api.model.TransactionsPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TransactionFilterServiceImpl implements TransactionFilterService {

    private final TransactionRepository carRepository;
    private final TransactionMapper carMapper;

    public TransactionFilterServiceImpl(TransactionRepository carRepository,
                                        TransactionMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public TransactionsPageResponse<TransactionDto> searchCars(TransactionsFilter filter, int page, int size) {
        Specification<Transaction> spec = TransactionSpec.filterBy(filter);
        Page<Transaction> pageResult = carRepository.findAll(spec, PageRequest.of(page, size));
        return carMapper.toCarsPageResponse(pageResult);
    }
}