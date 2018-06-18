package com.kryspinmusiol.monumentinventoryapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
/**
 *  DTO
 */
public class AddressCommand {

    @NotNull
    @Size(min=1, max = 255)
    @Column(nullable = false)
    private String street;

    @NotNull
    @Min(1)
    private Integer streetNumber;

    private String additionalInformation1;

    private String additionalInformation2;

    @Valid
    private CityCommand city;
}
