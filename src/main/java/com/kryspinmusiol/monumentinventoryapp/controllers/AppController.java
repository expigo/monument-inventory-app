package com.kryspinmusiol.monumentinventoryapp.controllers;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping({"/app/app-show-monument/{id}"})
    public String getShowMonument(@PathVariable String id, Model model) {

        model.addAttribute("monument", monumentService.findById(new Long(id)));

        return "app/app-show-monument";
    }

    @RequestMapping("/app/app-search-result")
    public String getSearchResult(Model model) {
        log.debug("Getting search result...");

        // todo: change to show the actual search result
        model.addAttribute("result", monumentService.getMonuments());


        return "app/app-search-result";
    }


    @RequestMapping("app/add-monument")
    public String newMonument(Model model) {
        model.addAttribute("monument", new MonumentCommand());

        return "/app/add-monument";
    }

    @PostMapping("/app")
    public String saveOrUpdate(@ModelAttribute MonumentCommand command) {
        MonumentCommand commandSaved = monumentService.saveMonumentCommand(command);

        return "redirect:/app/app-show-monument/" + commandSaved.getId();
    }




}
