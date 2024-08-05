package com.louly.soft.money.transfer.rest.api.mapper;


import com.louly.soft.money.transfer.rest.api.controller.criteria.TransactionsSearchCriteria;
import com.louly.soft.money.transfer.rest.api.dao.Transaction;
import com.louly.soft.money.transfer.rest.api.dao.filter.TransactionsFilter;
import com.louly.soft.money.transfer.rest.api.model.TransactionDto;
import com.louly.soft.money.transfer.rest.api.model.TransactionsPageResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR
)
public interface TransactionMapper {

    TransactionsFilter toFilter(TransactionsSearchCriteria searchCriteria);

    TransactionDto toCarDto(Transaction car);

    List<TransactionDto> toCarDtos(List<Transaction> cars);

    default TransactionsPageResponse<TransactionDto> toCarsPageResponse(Page<Transaction> page) {
        TransactionsPageResponse<TransactionDto> pageResponse = new TransactionsPageResponse<>();
        pageResponse.setContent(toCarDtos(page.getContent()));
        pageResponse.setPage(page.getNumber());
        pageResponse.setSize(page.getSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalSize(page.getTotalElements());
        return pageResponse;
    }

}