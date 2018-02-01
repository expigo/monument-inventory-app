package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;

import java.util.List;

public interface MonumentService {

    List<Monument> getMonuments();

    Monument findById(Long id);

    MonumentCommand saveMonumentCommand(MonumentCommand monumentCommand);

    MonumentCommand findMonumentCommandById(Long id);

    void deleteById(Long id);
}
