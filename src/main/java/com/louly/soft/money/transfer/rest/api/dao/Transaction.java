package com.louly.soft.money.transfer.rest.api.dao;


import com.louly.soft.money.transfer.rest.api.controller.criteria.FuelType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Transaction")
@Data
public class Transaction {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "make")
    private String make;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "num_of_doors")
    private int numberOfDoors;

    @Column(name = "price")
    private BigDecimal price;
}