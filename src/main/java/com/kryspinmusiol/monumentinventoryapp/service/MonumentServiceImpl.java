package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MonumentServiceImpl implements MonumentService {

    private final MonumentRepository monumentRepository;

    @Autowired
    public MonumentServiceImpl(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    public List<Monument> getMonuments() {
        log.debug("Service called...");

        List<Monument> monuments = new ArrayList<>();
        monumentRepository.findAll().iterator().forEachRemaining(monuments::add);

        return monuments;
    }

    @Override
    public Monument findById(Long id) {

        Optional<Monument> monumentOptional = monumentRepository.findById(id);

        if(!monumentOptional.isPresent()) {
            throw new RuntimeException("Monument not found!");
        }

        return monumentOptional.get();
    }
}
