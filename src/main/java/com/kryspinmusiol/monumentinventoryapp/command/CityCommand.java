package com.kryspinmusiol.monumentinventoryapp.command;

import com.kryspinmusiol.monumentinventoryapp.model.AdministrativeArea;

import javax.persistence.Embedded;

public class CityCommand {

    private String name;

    private String zipcode;

    private AdministrativeAreaCommand administrativeArea;

}
