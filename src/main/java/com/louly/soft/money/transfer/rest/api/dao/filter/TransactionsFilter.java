package com.louly.soft.money.transfer.rest.api.dao.filter;


import com.louly.soft.money.transfer.rest.api.controller.criteria.FuelType;

import java.math.BigDecimal;

public record TransactionsFilter(String make, FuelType fuelType, Integer numberOfDoors, BigDecimal priceFrom, BigDecimal priceTo) {
}