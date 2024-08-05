package com.louly.soft.money.transfer.rest.api.dao.specification;


import com.louly.soft.money.transfer.rest.api.controller.criteria.FuelType;
import com.louly.soft.money.transfer.rest.api.dao.Transaction;
import com.louly.soft.money.transfer.rest.api.dao.filter.TransactionsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TransactionSpec {

    public static final String MAKE = "make";
    public static final String FUEL_TYPE = "fuelType";
    public static final String NUMBER_OF_DOORS = "numberOfDoors";
    public static final String PRICE = "price";

    private TransactionSpec() {
        //empty
    }

    public static Specification<Transaction> filterBy(TransactionsFilter carsFilter) {
        return Specification
                .where(hasMake(carsFilter.make()))
                .and(hasFuelType(carsFilter.fuelType()))
                .and(hasNumberOfDoors(carsFilter.numberOfDoors()))
                .and(hasPriceGreaterThan(carsFilter.priceFrom()))
                .and(hasPriceLessThan(carsFilter.priceTo()));
    }

    private static Specification<Transaction> hasMake(String make) {
        return ((root, query, cb) -> make == null || make.isEmpty() ? cb.conjunction() : cb.equal(root.get(MAKE), make));
    }

    private static Specification<Transaction> hasFuelType(FuelType fuelType) {
        return (root, query, cb) -> fuelType == null ? cb.conjunction() : cb.equal(root.get(FUEL_TYPE), fuelType);
    }

    private static Specification<Transaction> hasNumberOfDoors(Integer numberOfDoors) {
        return (root, query, cb) -> numberOfDoors == null ? cb.conjunction() : cb.equal(root.get(NUMBER_OF_DOORS), numberOfDoors);
    }

    private static Specification<Transaction> hasPriceGreaterThan(BigDecimal priceFrom) {
        return (root, query, cb) -> priceFrom == null ? cb.conjunction() : cb.greaterThan(root.get(PRICE), priceFrom);
    }

    private static Specification<Transaction> hasPriceLessThan(BigDecimal priceTo) {
        return (root, query, cb) -> priceTo == null ? cb.conjunction() : cb.lessThan(root.get(PRICE), priceTo);
    }
}