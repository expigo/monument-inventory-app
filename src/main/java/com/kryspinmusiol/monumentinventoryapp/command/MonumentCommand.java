package com.kryspinmusiol.monumentinventoryapp.command;


import com.kryspinmusiol.monumentinventoryapp.model.Accessibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MonumentCommand {


    private Long id;

    @NotNull
    @Size(min=3, max = 255)
    private String name;

    @NotNull
    @Size(min=1, max = 255)
    private String timeOfOrigin;

    @NotNull
    @Size(min=1, max = 255)
    private String formsOfProtection;

    @NotNull
    @Size(min=1, max = 255)
    private String description;

    private Byte[] image;

    private Accessibility accessibility;

    @Valid
    private AddressCommand address;

}
