package com.kryspinmusiol.monumentinventoryapp.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AdministrativeAreaCommand {

    @NotNull
    @Size(min=3, max = 255)
    private String province;

    @NotNull
    @Size(min=3, max = 255)
    private String district;

    @NotNull
    @Size(min=3, max = 255)
    private String commune;
}
