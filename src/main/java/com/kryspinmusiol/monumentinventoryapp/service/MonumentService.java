package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;

import java.util.List;

public interface MonumentService {

    List<Monument> getMonuments();

    Monument findById(Long id);

}
