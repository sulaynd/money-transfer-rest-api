package com.louly.soft.money.transfer.rest.api.dao.interfaces;

public interface Currency extends Validatable {

    int ISO_CODE_LENGTH = 3;

    String getISOCode();
}
