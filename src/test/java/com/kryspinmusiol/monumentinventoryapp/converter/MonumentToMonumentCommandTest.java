package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonumentToMonumentCommandTest {

    private static final String ZIP_CODE = "44-100";
    private static final String CITY_NAME = "Gliwice";

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private static final String STREET = "5th Avenue";
    private static final String INFO1 = "info one";
    private static final String INFO2 = "info two";

    private static final Long MONUMENT_ID = 8L;
    private static final String MONUMENT_NAME = "Castle";
    private static final String DESCRIPTION = "Few words on the monument";
    private static final String ORIGIN = "Xw.";
    private static final String PROTECTION = "MZKP";
    private static final Accessibility MONUMENT_ACCESSIBILITY= Accessibility.PERMITTED;



    private MonumentToMonumentCommand monumentToMonumentCommand;

    @Before
    public void setUp() throws Exception {
        monumentToMonumentCommand = new MonumentToMonumentCommand(new AddressToAddressCommand(new CityToCityCommand(new AdministrativeAreaToAdministrativeAreaCommand())));
    }

    @Test
    public void testNull() throws Exception {
        assertNull(monumentToMonumentCommand.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(monumentToMonumentCommand.convert(new Monument()));
    }


    @Test
    public void convert() throws Exception {
        // given
        Monument monument = new Monument();
        monument.setId(MONUMENT_ID);
        monument.setName(MONUMENT_NAME);
        monument.setDescription(DESCRIPTION);
        monument.setFormsOfProtection(PROTECTION);
        monument.setTimeOfOrigin(ORIGIN);
        monument.setAccessibility(MONUMENT_ACCESSIBILITY);

        Address address = new Address();
        address.setStreet(STREET);
        address.setStreetNumber(20);
        address.setAdditionalInformation1(INFO1);
        address.setAdditionalInformation2(INFO2);

        City city = new City();
        city.setZipcode(ZIP_CODE);
        city.setName(CITY_NAME);

        AdministrativeArea administrativeArea = new AdministrativeArea();
        administrativeArea.setCommune(COMMUNE);
        administrativeArea.setDistrict(DISTRICT);
        administrativeArea.setProvince(PROVINCE);

        city.setAdministrativeArea(administrativeArea);

        address.setCity(city);

        monument.setAddress(address);

        // when

        MonumentCommand monumentCommand = monumentToMonumentCommand.convert(monument);

        // then
        assertEquals(ZIP_CODE, monumentCommand.getAddress().getCity().getZipcode());
        assertEquals(CITY_NAME, monumentCommand.getAddress().getCity().getName());
        assertEquals(COMMUNE, monumentCommand.getAddress().getCity().getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, monumentCommand.getAddress().getCity().getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, monumentCommand.getAddress().getCity().getAdministrativeArea().getProvince());
        assertEquals(STREET, monumentCommand.getAddress().getStreet());
        assertEquals(INFO1, monumentCommand.getAddress().getAdditionalInformation1());
        assertEquals(INFO2, monumentCommand.getAddress().getAdditionalInformation2());
        assertEquals(MONUMENT_ID, monumentCommand.getId());
        assertEquals(MONUMENT_NAME, monumentCommand.getName());
        assertEquals(DESCRIPTION, monumentCommand.getDescription());
        assertEquals(PROTECTION, monumentCommand.getFormsOfProtection());
        assertEquals(ORIGIN, monumentCommand.getTimeOfOrigin());
        assertEquals(MONUMENT_ACCESSIBILITY, monumentCommand.getAccessibility());
    }

}