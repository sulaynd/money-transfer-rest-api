/*
 * Copyright (c) 2018-2022. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.louly.soft.money.transfer.rest.api.service.parties;

import io.github.mfvanek.money.transfer.enums.PartyType;
import io.github.mfvanek.money.transfer.interfaces.Party;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class AbstractParty implements Party {

    private final Long id;
    private final PartyType partyType;
    private final String taxIdentificationNumber;

    AbstractParty(Long id, PartyType partyType, String taxIdentificationNumber) {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(partyType, "PartyType cannot be null");
        Objects.requireNonNull(taxIdentificationNumber, "Tax identification number cannot be null");

        this.id = id;
        this.partyType = partyType;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    @Override
    public final boolean isPrivatePerson() {
        return PartyType.PRIVATE_PERSON == partyType;
    }

    @Override
    public final boolean isLegalPerson() {
        return PartyType.LEGAL_PERSON == partyType;
    }

    @Override
    public final String toString() {
        return String.format("Party{%s, type=%s, tax identification number=%s, id=%d}",
                getName(), getPartyType(), getTaxIdentificationNumber(), getId());
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    public static Party makeLegalPerson(Long id, String taxIdentificationNumber, String name) {
        return new RussianLegalPerson(id, taxIdentificationNumber, name);
    }

    public static Party makePrivatePerson(Long id, String taxIdentificationNumber, String firstName, String lastName) {
        return new RussianPrivatePerson(id, taxIdentificationNumber, firstName, lastName);
    }

    public static Party getInvalid() {
        return InvalidParty.getInstance();
    }
}
