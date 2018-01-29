package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministrativeAreaCommandToAdministrativeAreaTest {

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    AdministrativeAreaCommandToAdministrativeArea administrativeAreaCommandToAdministrativeArea;

    @Before
    public void setUp() throws Exception {
        administrativeAreaCommandToAdministrativeArea = new AdministrativeAreaCommandToAdministrativeArea();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(administrativeAreaCommandToAdministrativeArea.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(administrativeAreaCommandToAdministrativeArea.convert(new AdministrativeAreaCommand()));
    }

    @Test
    public void convert() throws Exception {

        // given
        AdministrativeAreaCommand administrativeAreaCommand = new AdministrativeAreaCommand();
        administrativeAreaCommand.setCommune(COMMUNE);
        administrativeAreaCommand.setDistrict(DISTRICT);
        administrativeAreaCommand.setProvince(PROVINCE);

        // when
        AdministrativeArea administrativeArea = administrativeAreaCommandToAdministrativeArea.convert(administrativeAreaCommand);

        // then
        assertEquals(COMMUNE, administrativeArea.getCommune());
        assertEquals(DISTRICT, administrativeArea.getDistrict());
        assertEquals(PROVINCE, administrativeArea.getProvince());



    }

}