package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class MonumentServiceImplTest {

    MonumentServiceImpl monumentService;

    @Mock
    MonumentRepository monumentRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        monumentService = new MonumentServiceImpl(monumentRepository);
    }


    @Test
    public void getMonumentsTest() throws Exception {

        Monument mon = new Monument();
        List<Monument> monumentsData = new ArrayList<>();
        monumentsData.add(mon);

        when(monumentService.getMonuments()).thenReturn(monumentsData);

        List<Monument> monuments = monumentService.getMonuments();

        assertEquals(monuments.size(), 1);
        verify(monumentRepository,times(1)).findAll();
        verify(monumentRepository,never()).findById(anyLong());

    }

    @Test
    public void getMonumentByIdTest() throws Exception {

        Monument mon = new Monument();
        mon.setId(8L);

        Optional<Monument> monumentOptional = Optional.of(mon);

        when(monumentRepository.findById(anyLong())).thenReturn(monumentOptional);

        Monument monReturned = monumentService.findById(8L);

        assertNotNull("Null monument is back", monReturned);
        verify(monumentRepository, times(1)).findById(anyLong());
        verify(monumentRepository, never()).findAll();
    }


}
