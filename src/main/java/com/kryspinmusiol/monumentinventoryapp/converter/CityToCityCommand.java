package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AdministrativeAreaCommand;
import com.kryspinmusiol.monumentinventoryapp.command.CityCommand;
import com.kryspinmusiol.monumentinventoryapp.model.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityToCityCommand implements Converter<City, CityCommand> {

    private final AdministrativeAreaToAdministrativeAreaCommand administrativeAreaConverter;

    public CityToCityCommand(AdministrativeAreaToAdministrativeAreaCommand administrativeAreaConverter) {
        this.administrativeAreaConverter = administrativeAreaConverter;
    }

    @Nullable
    @Override
    public CityCommand convert(City city) {

        if(city == null) {
            return null;
        }

        CityCommand cityCommand = new CityCommand();

        cityCommand.setName(city.getName());
        cityCommand.setZipcode(city.getZipcode());
        cityCommand.setAdministrativeArea(administrativeAreaConverter.convert(city.getAdministrativeArea()));

        return cityCommand;
}
}
