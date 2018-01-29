package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Address;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressToAddressCommandTest {

    private static final String ZIP_CODE = "44-100";
    private static final String CITY_NAME = "Gliwice";

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private static final String STREET = "5th Avenue";
    private static final String INFO1 = "info one";
    private static final String INFO2 = "info two";

    private AddressToAddressCommand addressToAddressCommand;

    @Before
    public void setUp() throws Exception {
        addressToAddressCommand = new AddressToAddressCommand(new CityToCityCommand(new AdministrativeAreaToAdministrativeAreaCommand()));
    }

    @Test
    public void testNull() throws Exception {
        assertNull(addressToAddressCommand.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(addressToAddressCommand.convert(new Address()));
    }

    @Test
    public void convert() throws Exception {
        // given
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

        // when

        AddressCommand addressCommand = addressToAddressCommand.convert(address);

        // then
        assertEquals(ZIP_CODE, addressCommand.getCity().getZipcode());
        assertEquals(CITY_NAME, addressCommand.getCity().getName());
        assertEquals(COMMUNE, addressCommand.getCity().getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, addressCommand.getCity().getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, addressCommand.getCity().getAdministrativeArea().getProvince());
        assertEquals(STREET, addressCommand.getStreet());
        assertEquals(INFO1, addressCommand.getAdditionalInformation1());
        assertEquals(INFO2, addressCommand.getAdditionalInformation2());
    }

}