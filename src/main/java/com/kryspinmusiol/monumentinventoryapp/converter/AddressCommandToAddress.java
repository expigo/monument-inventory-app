package com.kryspinmusiol.monumentinventoryapp.converter;

import com.kryspinmusiol.monumentinventoryapp.command.AddressCommand;
import com.kryspinmusiol.monumentinventoryapp.model.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address> {

    private final CityCommandToCity cityConverter;

    public AddressCommandToAddress(CityCommandToCity cityConverter) {
        this.cityConverter = cityConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Address convert(AddressCommand addressCommand) {

        if (addressCommand == null) {
            return null;
        }

        Address address = new Address();
        address.setStreetNumber(addressCommand.getStreetNumber());
        address.setStreet(addressCommand.getStreet());
        address.setAdditionalInformation1(addressCommand.getAdditionalInformation1());
        address.setAdditionalInformation2(addressCommand.getAdditionalInformation2());

        address.setCity(cityConverter.convert(addressCommand.getCity()));

        return address;
    }
}
