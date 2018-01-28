package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class MonumentCommandToMonument implements Converter<MonumentCommand, Monument> {

    private final AddressCommandToAddress addressConverter;

    public MonumentCommandToMonument(AddressCommandToAddress addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Monument convert(MonumentCommand monumentCommand) {
        if(monumentCommand == null) {
            return null;
        }

        Monument monument = new Monument();

        monument.setId(monumentCommand.getId());
        monument.setName(monumentCommand.getName());
        monument.setDescription(monumentCommand.getDescription());
        monument.setTimeOfOrigin(monumentCommand.getTimeOfOrigin());
        monument.setFormsOfProtection(monumentCommand.getFormsOfProtection());
        monument.setRepresentative(monumentCommand.isRepresentative());

        monument.setAddress(addressConverter.convert(monumentCommand.getAddress()));

        return monument;
    }
}
