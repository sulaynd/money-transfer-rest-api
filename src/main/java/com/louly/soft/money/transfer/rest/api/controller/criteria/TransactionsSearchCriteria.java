package com.louly.soft.money.transfer.rest.api.controller.criteria;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionsSearchCriteria {

    private String make;
    private FuelType fuelType;
    private Integer numberOfDoors;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private int page = 0;
    private int size = 10;
}