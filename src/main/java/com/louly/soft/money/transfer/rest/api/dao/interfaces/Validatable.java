package com.louly.soft.money.transfer.rest.api.dao.interfaces;

public interface Validatable {

    boolean isValid();

    default boolean isNotValid() {
        return !isValid();
    }
}
