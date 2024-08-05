package com.louly.soft.money.transfer.rest.api.controller;


import com.louly.soft.money.transfer.rest.api.controller.criteria.TransactionsSearchCriteria;
import com.louly.soft.money.transfer.rest.api.mapper.TransactionMapper;
import com.louly.soft.money.transfer.rest.api.model.TransactionDto;
import com.louly.soft.money.transfer.rest.api.model.TransactionsPageResponse;
import com.louly.soft.money.transfer.rest.api.service.TransactionFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionFilterService carFilterService;
    private final TransactionMapper carMapper;

    public TransactionController(TransactionFilterService carFilterService,
                                 TransactionMapper carMapper) {
        this.carFilterService = carFilterService;
        this.carMapper = carMapper;
    }

    @GetMapping(value = "/cars")
    @ResponseStatus(HttpStatus.OK)
    TransactionsPageResponse<TransactionDto> getCars(TransactionsSearchCriteria criteria) {
        return carFilterService.searchCars(carMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
    }

}