package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentCommandToMonument;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentToMonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.exception.NotFoundException;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
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

    @Mock
    MonumentToMonumentCommand monumentToMonumentCommand;

    @Mock
    MonumentCommandToMonument monumentCommandToMonument;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        monumentService = new MonumentServiceImpl(monumentRepository, monumentCommandToMonument, monumentToMonumentCommand);
    }


    @Test
    public void testGetMonuments() throws Exception {

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
    public void testGetMonumentById() throws Exception {

        Monument mon = new Monument();
        mon.setId(8L);

        Optional<Monument> monumentOptional = Optional.of(mon);

        when(monumentRepository.findById(anyLong())).thenReturn(monumentOptional);

        Monument monReturned = monumentService.findById(8L);

        assertNotNull("Null monument is back", monReturned);
        verify(monumentRepository, times(1)).findById(anyLong());
        verify(monumentRepository, never()).findAll();
    }

    @Test (expected = NotFoundException.class)
    public void testGetMonumentByIdNotFound() throws Exception {

        Optional<Monument> monumentOptional = Optional.empty();

        when(monumentRepository.findById(anyLong())).thenReturn(monumentOptional);

        Monument monumentReturned = monumentService.findById(8L);
    }

    @Test (expected = NumberFormatException.class)
    public void testGetMonumentNumberFormatException() throws Exception {
        monumentRepository.findById(Long.valueOf("someDummyStringInput"));
    }


    // command object tests

    @Test
    public void testGetMonumentCommandById() throws Exception {

        Monument mon = new Monument();
        mon.setId(8L);
        Optional<Monument> monumentOptional = Optional.of(mon);

        when(monumentRepository.findById(any())).thenReturn(monumentOptional);

        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(8L);

        when(monumentToMonumentCommand.convert(any())).thenReturn(monumentCommand);

        MonumentCommand monumentCommandFoundById = monumentService.findMonumentCommandById(8L);

        assertNotNull("Null object returned", monumentCommandFoundById);
        verify(monumentRepository, times(1)).findById(anyLong());
        verify(monumentRepository, never()).findAll();
    }
}
