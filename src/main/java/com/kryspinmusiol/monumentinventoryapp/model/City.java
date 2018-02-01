package com.kryspinmusiol.monumentinventoryapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@EqualsAndHashCode(exclude = {"administrativeArea"})
@Embeddable
public class City {

    @Column(name = "city_name")
    private String name;

    @Column(name = "zip_code")
    private String zipcode;

    @Embedded
    private AdministrativeArea administrativeArea;

}
