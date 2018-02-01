package com.kryspinmusiol.monumentinventoryapp.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CityCommand {


    @NotNull
    @Size(min = 1, message="wymagane")
    private String name;

    @NotNull
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Nieprawidłowy format")
    private String zipcode;

    @Valid
    private AdministrativeAreaCommand administrativeArea;

}
