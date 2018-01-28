package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class AdministrativeAreaToAdministrativeAreaCommand implements Converter<AdministrativeArea, AdministrativeAreaCommand>{

    @Synchronized
    @Nullable
    @Override
    public AdministrativeAreaCommand convert(AdministrativeArea administrativeArea) {

        if(administrativeArea == null) {
            return null;
        }

        final AdministrativeAreaCommand administrativeAreaCommand= new AdministrativeAreaCommand();

        administrativeAreaCommand.setProvince(administrativeArea.getProvince());
        administrativeAreaCommand.setDistrict(administrativeArea.getDistrict());
        administrativeAreaCommand.setCommune(administrativeArea.getCommune());

        return administrativeAreaCommand;

    }
}
