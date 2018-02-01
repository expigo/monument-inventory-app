package com.kryspinmusiol.monumentinventoryapp.repository;


import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MonumentRepository extends CrudRepository<Monument, Long> {

    Optional<Monument> findByName(String name);

    Optional<Monument> findByDescription(String description);

    List<Monument> findByNameContainingIgnoreCase(String name);

    List<Monument> findByTimeOfOriginContainingIgnoreCase(String origin);

    List<Monument> findByAddressStreetContainingIgnoreCase(String street);

    List<Monument> findByFormsOfProtectionContainingIgnoreCase(String protection);

}
