/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

import java.util.Collection;

public interface PagedResult<T> {

    boolean hasMore();

    Collection<T> getContent();

    int getPageNumber();

    int getRecordsPerPage();
}
