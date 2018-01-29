package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Address;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressCommandToAddressTest {

    private static final String ZIP_CODE = "44-100";
    private static final String CITY_NAME = "Gliwice";

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private static final String STREET = "5th Avenue";
    private static final String INFO1 = "info one";
    private static final String INFO2 = "info two";


    private AddressCommandToAddress addressCommandToAddress;

    @Before
    public void setUp() throws Exception {
        addressCommandToAddress = new AddressCommandToAddress(new CityCommandToCity(new AdministrativeAreaCommandToAdministrativeArea()));
    }


    @Test
    public void testNull() throws Exception {
        assertNull(addressCommandToAddress.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(addressCommandToAddress.convert(new AddressCommand()));
    }

    @Test
    public void convert() throws Exception {

        // given
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

        // when

        Address address = addressCommandToAddress.convert(addressCommand);

        // then
        assertEquals(ZIP_CODE, address.getCity().getZipcode());
        assertEquals(CITY_NAME, address.getCity().getName());
        assertEquals(COMMUNE, address.getCity().getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, address.getCity().getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, address.getCity().getAdministrativeArea().getProvince());
        assertEquals(STREET, address.getStreet());
        assertEquals(INFO1, address.getAdditionalInformation1());
        assertEquals(INFO2, address.getAdditionalInformation2());
    }
}