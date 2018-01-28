package com.kryspinmusiol.monumentinventoryapp.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministrativeAreaCommand {
    private String province;

    private String district;

    private String commune;
}
