/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

public interface Pageable<T> {

    PagedResult<T> getAll(int pageNumber, int recordsPerPage);

    default PagedResult<T> getAll(Pagination pagination) {
        return getAll(pagination.getPageNumber(), pagination.getRecordsPerPage());
    }
}
