/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.parties;

final class RussianLegalPerson extends LegalPerson {

    // Without 'Reason code from registration' (КПП)

    RussianLegalPerson(Long id, String taxIdentificationNumber, String name) {
        super(id, taxIdentificationNumber, name);
        validateTaxIdentificationNumber(taxIdentificationNumber);
    }

    private static void validateTaxIdentificationNumber(String taxIdentificationNumber) {
        if (taxIdentificationNumber.length() != 10) {
            throw new IllegalArgumentException("Tax identification number must contain 10 characters");
        }
    }
}
