package com.louly.soft.money.transfer.rest.api.service.parties;


import com.louly.soft.money.transfer.rest.api.dao.interfaces.Identifiable;
import com.louly.soft.money.transfer.rest.api.enums.PartyType;

public interface Party extends Identifiable {

    String getName();

    boolean isPrivatePerson();

    boolean isLegalPerson();

    PartyType getPartyType();

    String getTaxIdentificationNumber();
}
