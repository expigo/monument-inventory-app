package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand>{

    private final CityToCityCommand cityConverter;

    public AddressToAddressCommand(CityToCityCommand cityConverter) {
        this.cityConverter = cityConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public AddressCommand convert(Address address) {

        if(address == null) {
            return null;
        }

        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setStreetNumber(address.getStreetNumber());
        addressCommand.setStreet(address.getStreet());
        addressCommand.setAdditionalInformation1(address.getAdditionalInformation1());
        addressCommand.setAdditionalInformation2(address.getAdditionalInformation2());

        addressCommand.setCity(cityConverter.convert(address.getCity()));


        return addressCommand;
    }
}
