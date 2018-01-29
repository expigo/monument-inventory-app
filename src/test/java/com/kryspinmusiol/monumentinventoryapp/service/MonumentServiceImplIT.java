package com.kryspinmusiol.monumentinventoryapp.service;


import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentCommandToMonument;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentToMonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest // to call the whole spring boot application context
public class MonumentServiceImplIT {

    private static final String NEW_NAME = "New name";

    @Autowired
    MonumentService monumentService;

    @Autowired
    MonumentRepository monumentRepository;

    @Autowired
    MonumentCommandToMonument monumentCommandToMonument;

    @Autowired
    MonumentToMonumentCommand monumentToMonumentCommand;

    @Test
    public void testSaveName() throws Exception {

        // given
        Iterable<Monument> monuments = monumentRepository.findAll();
        Monument monumentTest = monuments.iterator().next();  // any will do for testing purposes
        MonumentCommand monumentCommandTest = monumentToMonumentCommand.convert(monumentTest);

        // when
        monumentCommandTest.setName(NEW_NAME);
        MonumentCommand monumentCommandSaved = monumentService.saveMonumentCommand(monumentCommandTest);

        //then
        // not all properties tested, but it is enough to confirm that it is working
        assertEquals(NEW_NAME, monumentCommandSaved.getName());
        assertEquals(monumentTest.getId(), monumentCommandSaved.getId());
        assertEquals(monumentTest.getAddress().getCity().getName(), monumentCommandSaved.getAddress().getCity().getName());
        assertEquals(monumentTest.getAddress().getCity().getAdministrativeArea().getCommune(), monumentCommandSaved.getAddress().getCity().getAdministrativeArea().getCommune());

    }
}
