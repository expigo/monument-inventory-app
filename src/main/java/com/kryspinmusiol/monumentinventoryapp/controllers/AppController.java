package com.kryspinmusiol.monumentinventoryapp.controllers;

import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class AppController {

    MonumentService monumentService;

    @Autowired
    public AppController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    @RequestMapping({"/app/app-main"})
    public String getAppMain(Model model) {
        log.debug("Getting index page");

        return "app/app-main";
    }

    @RequestMapping({"/app/advanced-search"})
    public String getAppSearchAdvanced(Model model) {
        log.debug("Getting index page");

        return "app/advanced-search";
    }

    @RequestMapping({"/app/add-monument"})
    public String getAddMonument(Model model) {


        return "app/add-monument";
    }

    @RequestMapping({"/app/show-monument/{id}"})
    public String getShowMonument(@PathVariable String id, Model model) {

        model.addAttribute("monument", monumentService.findById(new Long(id)));


        return "app/show-monument";
    }

}
