package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentCommandToMonument;
import com.kryspinmusiol.monumentinventoryapp.converter.MonumentToMonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MonumentServiceImpl implements MonumentService {

    private final MonumentRepository monumentRepository;
    private final MonumentCommandToMonument monumentCommandToMonument;
    private final MonumentToMonumentCommand monumentToMonumentCommand;


    @Autowired
    public MonumentServiceImpl(MonumentRepository monumentRepository, MonumentCommandToMonument monumentCommandToMonument, MonumentToMonumentCommand monumentToMonumentCommand) {
        this.monumentRepository = monumentRepository;
        this.monumentCommandToMonument = monumentCommandToMonument;
        this.monumentToMonumentCommand = monumentToMonumentCommand;
    }




    @Override
    public List<Monument> getMonuments() {
        log.debug("Service called...");

        List<Monument> monuments = new ArrayList<>();
        monumentRepository.findAll().iterator().forEachRemaining(monuments::add);

        return monuments;
    }

    @Override
    public Monument findById(Long id) {

        Optional<Monument> monumentOptional = monumentRepository.findById(id);

        if(!monumentOptional.isPresent()) {
            throw new RuntimeException("Monument not found!");
        }

        return monumentOptional.get();
    }

    @Override
    public MonumentCommand saveMonumentCommand(MonumentCommand monumentCommand) {

        Monument monumentDetached = monumentCommandToMonument.convert(monumentCommand);

        Monument monumentSaved = monumentRepository.save(monumentDetached);
        log.debug("Monument saved (id): " + monumentSaved.getId());
        return monumentToMonumentCommand.convert(monumentSaved);
    }
}
