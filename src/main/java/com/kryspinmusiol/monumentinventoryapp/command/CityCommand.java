package com.kryspinmusiol.monumentinventoryapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CityCommand {

    private String name;

    private String zipcode;

    private AdministrativeAreaCommand administrativeArea;

}
