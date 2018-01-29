package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministrativeAreaToAdministrativeAreaCommandTest {

    private static final String COMMUNE = "Gliwice";
    private static final String DISTRICT = "gliwcki";
    private static final String PROVINCE = "Silesia";

    private AdministrativeAreaToAdministrativeAreaCommand administrativeAreaToAdministrativeAreaCommand;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        administrativeAreaToAdministrativeAreaCommand = new AdministrativeAreaToAdministrativeAreaCommand();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(administrativeAreaToAdministrativeAreaCommand.convert(null));
    }

    /**
     * tests the case is empty object is passed
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(administrativeAreaToAdministrativeAreaCommand.convert(new AdministrativeArea()));
    }


    @Test
    public void convert() throws Exception {

        // given
        AdministrativeArea administrativeArea = new AdministrativeArea();
        administrativeArea.setDistrict(DISTRICT);
        administrativeArea.setCommune(COMMUNE);
        administrativeArea.setProvince(PROVINCE);

        // when
        AdministrativeAreaCommand administrativeAreaCommand = administrativeAreaToAdministrativeAreaCommand.convert(administrativeArea);


        // then
        assertEquals(COMMUNE, administrativeAreaCommand.getCommune());
        assertEquals(DISTRICT, administrativeAreaCommand.getDistrict());
        assertEquals(PROVINCE, administrativeAreaCommand.getProvince());
    }

}