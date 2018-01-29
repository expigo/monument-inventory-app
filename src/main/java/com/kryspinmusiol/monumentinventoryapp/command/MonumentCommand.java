package com.kryspinmusiol.monumentinventoryapp.command;


import com.kryspinmusiol.monumentinventoryapp.model.Accessibility;
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

    private Accessibility accessibility;

    private AddressCommand address;

}
