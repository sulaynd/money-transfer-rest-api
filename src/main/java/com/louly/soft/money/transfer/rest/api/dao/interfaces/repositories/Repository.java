/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

public interface Repository<T> extends Pageable<T> {

    int size();

    T getById(Long id);

    T getInvalid();
}
