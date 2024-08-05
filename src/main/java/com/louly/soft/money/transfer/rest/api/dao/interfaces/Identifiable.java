package com.louly.soft.money.transfer.rest.api.dao.interfaces;

public interface Identifiable extends Validatable {

    long INVALID_ID = -1L;

    Long getId();

    @Override
    default boolean isValid() {
        return INVALID_ID != getId();
    }
}
