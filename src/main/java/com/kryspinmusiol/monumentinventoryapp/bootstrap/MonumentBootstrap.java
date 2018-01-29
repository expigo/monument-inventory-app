package com.kryspinmusiol.monumentinventoryapp.bootstrap;

import com.kryspinmusiol.monumentinventoryapp.model.*;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MonumentBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private static final Accessibility MONUMENT_ACCESSIBILITY= Accessibility.PERMITTED;


    private final MonumentRepository monumentRepository;

//    @Autowired
    public MonumentBootstrap(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading bootstrap data");
        monumentRepository.saveAll(getMonuments());

    }

    private List<Monument> getMonuments() {


        List<Monument> monuments = new ArrayList<>();

        AdministrativeArea administrativeArea = new AdministrativeArea();
        City city = new City();
        Address address = new Address();
        Monument castleOnTheHill = new Monument();

        administrativeArea.setCommune("miasto Gliwice");
        administrativeArea.setDistrict("gliwicki");
        administrativeArea.setProvince("śląskie");

        city.setAdministrativeArea(administrativeArea);
        city.setName("Gliwice");
        city.setZipcode("44-100");




        // castle on the hill
        address.setCity(city);
        address.setStreet("Ogórkowa");
        address.setStreetNumber(19);
        address.setAdditionalInformation1("MHMM");

        castleOnTheHill.setAddress(address);
        castleOnTheHill.setDescription("HEHE");
        castleOnTheHill.setFormsOfProtection("MZKP");
        castleOnTheHill.setName("Church");
        castleOnTheHill.setTimeOfOrigin("XII wiek");
        castleOnTheHill.setAccessibility(MONUMENT_ACCESSIBILITY);
        castleOnTheHill.setId(new Long(1));


        monuments.add(castleOnTheHill);


        return monuments;
    }
}
