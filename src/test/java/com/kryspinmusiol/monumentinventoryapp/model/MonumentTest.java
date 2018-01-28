package com.kryspinmusiol.monumentinventoryapp.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MonumentTest {

    Monument monument;

    @Before
    public void setUp() throws Exception {
        monument = new Monument();
    }

    @Test
    public void getId() throws Exception {
        Long id = 15L;

        monument.setId(id);

        assertEquals(id, monument.getId());

    }
}
