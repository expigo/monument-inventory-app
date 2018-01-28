package com.kryspinmusiol.monumentinventoryapp.controllers;


import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    MonumentService monumentService;

    @Autowired
    public IndexController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    @RequestMapping({"","/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");

        model.addAttribute("monuments", monumentService.getMonuments());

        return "index";
    }
}
