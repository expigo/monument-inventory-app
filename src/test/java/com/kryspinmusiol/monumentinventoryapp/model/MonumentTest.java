package com.kryspinmusiol.monumentinventoryapp.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MonumentTest {

    private static final Long MONUMENT_ID = 15L;
    private static final Accessibility MONUMENT_ACCESSIBILITY= Accessibility.PERMITTED;

    Monument monument;

    @Before
    public void setUp() throws Exception {
        monument = new Monument();
    }

    @Test
    public void getId() throws Exception {

        monument.setId(MONUMENT_ID);

        assertEquals(MONUMENT_ID, monument.getId());

    }

    @Test
    public void getRepresentative() throws Exception {

        monument.setId(MONUMENT_ID);
        monument.setAccessibility(MONUMENT_ACCESSIBILITY);

        assertEquals(MONUMENT_ACCESSIBILITY, monument.getAccessibility());

    }
}
