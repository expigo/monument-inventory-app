package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityToCityCommandTest {

    private static final String ZIP_CODE = "44-100";
    private static final String CITY_NAME = "Gliwice";

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private CityToCityCommand cityToCityCommand;

    @Before
    public void setUp() throws Exception {
        cityToCityCommand = new CityToCityCommand(new AdministrativeAreaToAdministrativeAreaCommand());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(cityToCityCommand.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(cityToCityCommand.convert(new City()));
    }

    @Test
    public void convert() throws Exception {
        // given
        City city = new City();
        city.setZipcode(ZIP_CODE);
        city.setName(CITY_NAME);

        AdministrativeArea administrativeArea = new AdministrativeArea();
        administrativeArea.setCommune(COMMUNE);
        administrativeArea.setDistrict(DISTRICT);
        administrativeArea.setProvince(PROVINCE);

        city.setAdministrativeArea(administrativeArea);

        // when
        CityCommand cityCommand = cityToCityCommand.convert(city);


        // then
        assertEquals(ZIP_CODE, cityCommand.getZipcode());
        assertEquals(CITY_NAME, cityCommand.getName());
        assertEquals(COMMUNE, cityCommand.getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, cityCommand.getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, cityCommand.getAdministrativeArea().getProvince());
    }

}