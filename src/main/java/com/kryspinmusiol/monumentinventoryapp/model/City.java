package com.kryspinmusiol.monumentinventoryapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@EqualsAndHashCode(exclude = {"administrativeArea"})
@Embeddable
public class City {

    private String name;

    private String zipcode;

    @Embedded
    private AdministrativeArea administrativeArea;

}
