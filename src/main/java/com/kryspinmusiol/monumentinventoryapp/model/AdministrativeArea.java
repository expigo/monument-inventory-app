package com.kryspinmusiol.monumentinventoryapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@Embeddable
public class AdministrativeArea {

    private String province;

    private String district;

    private String commune;

}
