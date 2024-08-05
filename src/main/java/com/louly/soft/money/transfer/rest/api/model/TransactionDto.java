package com.louly.soft.money.transfer.rest.api.model;


import com.louly.soft.money.transfer.rest.api.controller.criteria.FuelType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class TransactionDto {

    private int id;
    private String make;
    private FuelType fuelType;
    private int numberOfDoors;
    private BigDecimal price;

}