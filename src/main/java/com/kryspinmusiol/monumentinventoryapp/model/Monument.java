package com.kryspinmusiol.monumentinventoryapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"address"})
@Entity
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String timeOfOrigin;

    private String formsOfProtection;

    private String description;

    private boolean isRepresentative;

    @Embedded
    private Address address;



}