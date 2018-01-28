package com.kryspinmusiol.monumentinventoryapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressCommand {

    private String street;

    private Integer streetNumber;

    private String additionalInformation1;

    private String additionalInformation2;

    private CityCommand city;
}
