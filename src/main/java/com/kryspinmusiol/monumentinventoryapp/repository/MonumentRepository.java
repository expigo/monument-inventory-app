package com.kryspinmusiol.monumentinventoryapp.repository;


import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MonumentRepository extends CrudRepository<Monument, Long> {

    Optional<Monument> findByName(String name);

    Optional<Monument> findByDescription(String description);

}
