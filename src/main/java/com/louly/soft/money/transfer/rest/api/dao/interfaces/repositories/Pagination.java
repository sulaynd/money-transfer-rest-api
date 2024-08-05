/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

import com.louly.soft.money.transfer.rest.api.dao.interfaces.Validatable;

public interface Pagination extends Validatable {

    int getRecordsPerPage();

    int getPageNumber();
}
