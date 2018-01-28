package com.kryspinmusiol.monumentinventoryapp.converter;


import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AdministrativeAreaCommandToAdministrativeArea implements Converter<AdministrativeAreaCommand, AdministrativeArea> {


    @Synchronized
    @Nullable
    @Override
    public AdministrativeArea convert(AdministrativeAreaCommand administrativeAreaCommand) {

        if(administrativeAreaCommand == null) {
            return null;
        }

        // final, so it is immutable (thread safety issue)
        final AdministrativeArea administrativeArea = new AdministrativeArea();
        administrativeArea.setProvince(administrativeAreaCommand.getProvince());
        administrativeArea.setDistrict(administrativeAreaCommand.getDistrict());
        administrativeArea.setCommune(administrativeAreaCommand.getCommune());

        return administrativeArea;
    }
}
