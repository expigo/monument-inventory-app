package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class CityCommandToCity implements Converter<CityCommand, City>{

    private final AdministrativeAreaCommandToAdministrativeArea administrativeAreaConverter;

    public CityCommandToCity(AdministrativeAreaCommandToAdministrativeArea administrativeAreaConverter) {
        this.administrativeAreaConverter = administrativeAreaConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public City convert(CityCommand cityCommand) {
        if(cityCommand == null) {
            return null;
        }

        City city = new City();

        city.setZipcode(cityCommand.getZipcode());
        city.setName(cityCommand.getName());
        city.setAdministrativeArea(administrativeAreaConverter.convert(cityCommand.getAdministrativeArea()));

        return city;
    }
}
