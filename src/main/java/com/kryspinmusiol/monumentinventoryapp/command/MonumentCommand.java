package com.kryspinmusiol.monumentinventoryapp.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonumentCommand {

    private Long id;

    private String name;

    private String timeOfOrigin;

    private String formsOfProtection;

    private String description;

    private boolean isRepresentative;

    private AddressCommand address;

}
