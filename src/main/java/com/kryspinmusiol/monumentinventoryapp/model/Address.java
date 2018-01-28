package com.kryspinmusiol.monumentinventoryapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"monument", "City"})
@Embeddable
public class Address {

    private String street;

    private Integer streetNumber;

    private String additionalInformation1;

    private String additionalInformation2;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "city_name"))
    })
    private City city;

    // todo: add street number
    // todo: validation
}
