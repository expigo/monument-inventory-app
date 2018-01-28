package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class MonumentToMonumentCommand implements Converter<Monument, MonumentCommand> {

    public MonumentToMonumentCommand(AddressToAddressCommand addressConverter) {
        this.addressConverter = addressConverter;
    }

    private final AddressToAddressCommand addressConverter;



    @Synchronized
    @Nullable
    @Override
    public MonumentCommand convert(Monument monument) {

        if(monument == null) {
            return null;
        }

        MonumentCommand monumentCommand = new MonumentCommand();

        monumentCommand.setId(monument.getId());
        monumentCommand.setName(monument.getName());
        monumentCommand.setDescription(monument.getDescription());
        monumentCommand.setTimeOfOrigin(monument.getTimeOfOrigin());
        monumentCommand.setFormsOfProtection(monument.getFormsOfProtection());
        monumentCommand.setRepresentative(monument.isRepresentative());

        monumentCommand.setAddress(addressConverter.convert(monument.getAddress()));

        return monumentCommand;
    }
}
