package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Accessibility;
import com.kryspinmusiol.monumentinventoryapp.model.Address;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonumentCommandToMonumentTest {

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



    private MonumentCommandToMonument monumentCommandToMonument;

    @Before
    public void setUp() throws Exception {
        monumentCommandToMonument = new MonumentCommandToMonument(new AddressCommandToAddress(new CityCommandToCity(new AdministrativeAreaCommandToAdministrativeArea())));
    }

    @Test
    public void testNull() throws Exception {
        assertNull(monumentCommandToMonument.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(monumentCommandToMonument.convert(new MonumentCommand()));
    }


    @Test
    public void convert() throws Exception {
        // given
        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(MONUMENT_ID);
        monumentCommand.setName(MONUMENT_NAME);
        monumentCommand.setDescription(DESCRIPTION);
        monumentCommand.setFormsOfProtection(PROTECTION);
        monumentCommand.setTimeOfOrigin(ORIGIN);
        monumentCommand.setAccessibility(MONUMENT_ACCESSIBILITY);

        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setStreet(STREET);
        addressCommand.setStreetNumber(20);
        addressCommand.setAdditionalInformation1(INFO1);
        addressCommand.setAdditionalInformation2(INFO2);

        CityCommand cityCommand = new CityCommand();
        cityCommand.setZipcode(ZIP_CODE);
        cityCommand.setName(CITY_NAME);

        AdministrativeAreaCommand administrativeAreaCommand = new AdministrativeAreaCommand();
        administrativeAreaCommand.setCommune(COMMUNE);
        administrativeAreaCommand.setDistrict(DISTRICT);
        administrativeAreaCommand.setProvince(PROVINCE);

        cityCommand.setAdministrativeArea(administrativeAreaCommand);

        addressCommand.setCity(cityCommand);

        monumentCommand.setAddress(addressCommand);

        // when

        Monument monument = monumentCommandToMonument.convert(monumentCommand);

        // then
        assertEquals(ZIP_CODE, monument.getAddress().getCity().getZipcode());
        assertEquals(CITY_NAME, monument.getAddress().getCity().getName());
        assertEquals(COMMUNE, monument.getAddress().getCity().getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, monument.getAddress().getCity().getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, monument.getAddress().getCity().getAdministrativeArea().getProvince());
        assertEquals(STREET, monument.getAddress().getStreet());
        assertEquals(INFO1, monument.getAddress().getAdditionalInformation1());
        assertEquals(INFO2, monument.getAddress().getAdditionalInformation2());
        assertEquals(MONUMENT_ID, monument.getId());
        assertEquals(MONUMENT_NAME, monument.getName());
        assertEquals(DESCRIPTION, monument.getDescription());
        assertEquals(PROTECTION, monument.getFormsOfProtection());
        assertEquals(ORIGIN, monument.getTimeOfOrigin());

    }

}