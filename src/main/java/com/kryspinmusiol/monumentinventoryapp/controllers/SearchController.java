package com.kryspinmusiol.monumentinventoryapp.controllers;


import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    MonumentRepository monumentRepository;

    public SearchController(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }

    @GetMapping("app/name-search")
    public String nameSearch(@RequestParam("monName") String name, Model model) {

        model.addAttribute("result", monumentRepository.findByNameContainingIgnoreCase(name));

        return "/app/app-search-result";
    }

    @GetMapping("app/street-search")
    public String streetSearch(@RequestParam("monStreet") String name, Model model) {

        model.addAttribute("result", monumentRepository.findByAddressStreetContainingIgnoreCase(name));

        return "/app/app-search-result";
    }

    @GetMapping("app/protection-search")
    public String protectionSearch(@RequestParam("monProtection") String name, Model model) {

        model.addAttribute("result", monumentRepository.findByFormsOfProtectionContainingIgnoreCase(name));

        return "/app/app-search-result";
    }


    @GetMapping("app/origin-search")
    public String originSearch(@RequestParam("monOrigin") String name, Model model) {

        model.addAttribute("result", monumentRepository.findByTimeOfOriginContainingIgnoreCase(name));

        return "/app/app-search-result";
    }
}
