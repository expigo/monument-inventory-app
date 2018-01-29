package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityCommandToCityTest {

    private static final String ZIP_CODE = "44-100";
    private static final String CITY_NAME = "Gliwice";

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private CityCommandToCity cityCommandToCity;

    @Before
    public void setUp() throws Exception {
        cityCommandToCity = new CityCommandToCity(new AdministrativeAreaCommandToAdministrativeArea());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(cityCommandToCity.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(cityCommandToCity.convert(new CityCommand()));
    }


    @Test
    public void convert() throws Exception {

        // given
        CityCommand cityCommand = new CityCommand();
        cityCommand.setZipcode(ZIP_CODE);
        cityCommand.setName(CITY_NAME);

        AdministrativeAreaCommand administrativeAreaCommand = new AdministrativeAreaCommand();
        administrativeAreaCommand.setCommune(COMMUNE);
        administrativeAreaCommand.setDistrict(DISTRICT);
        administrativeAreaCommand.setProvince(PROVINCE);

        cityCommand.setAdministrativeArea(administrativeAreaCommand);

        // when
        City city = cityCommandToCity.convert(cityCommand);


        // then
        assertEquals(ZIP_CODE, city.getZipcode());
        assertEquals(CITY_NAME, city.getName());
        assertEquals(COMMUNE, city.getAdministrativeArea().getCommune());
        assertEquals(DISTRICT, city.getAdministrativeArea().getDistrict());
        assertEquals(PROVINCE, city.getAdministrativeArea().getProvince());


    }

}