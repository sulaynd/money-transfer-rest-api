package com.louly.soft.money.transfer.rest.api.dao.interfaces.repositories;

public interface PartyRepository extends Repository<Party>, Cleanable {

    Party addLegalPerson(String taxIdentificationNumber, String name);

    Party addPrivatePerson(String taxIdentificationNumber, String firstName, String lastName);

    Party getOurBank();
}
