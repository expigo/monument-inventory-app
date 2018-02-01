package com.kryspinmusiol.monumentinventoryapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"monument", "City"})
@Embeddable
public class Address {

    private String street;

    @Column(name="street_number")
    private Integer streetNumber;

    @Column(name = "additional_information_1", nullable = true)
    private String additionalInformation1;

    @Column(name = "additional_information_2", nullable = true)
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
