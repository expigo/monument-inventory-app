package com.kryspinmusiol.monumentinventoryapp.controllers;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.exception.NotFoundException;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class AppController {

    MonumentService monumentService;

    @Autowired
    public AppController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }


    @GetMapping({"/app/app-main"})
    public String getAppMain(Model model) {
        log.debug("Getting index page");

        return "app/app-main";
    }


    @GetMapping({"/app/app-advanced-search"})
    public String getAppSearchAdvanced(Model model) {
        log.debug("Getting index page");

        return "app/app-advanced-search";
    }


    @GetMapping({"/app/{id}/app-show-monument"})
    public String getShowMonument(@PathVariable String id, Model model) {

        model.addAttribute("monument", monumentService.findById(new Long(id)));

        return "app/app-show-monument";
    }


    @GetMapping("/app/app-search-result")
    public String getSearchResult(Model model) {
        log.debug("Getting search result...");

        // todo: change to show the actual search result
        model.addAttribute("result", monumentService.getMonuments());


        return "app/app-search-result";
    }


    @GetMapping("app/add") // /app/app-add-monument fails test: view resolver dispatcher redirects back to the current handler URL (funny, because that how Spring doc does it)
    public String newMonument(Model model) {
        model.addAttribute("monument", new MonumentCommand());

        return "app/app-add-monument";
    }


//    @PostMapping("/app/save")
    @RequestMapping("/app/save")
    public String saveOrUpdate(@Valid @ModelAttribute("monument") MonumentCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(
                    objectError ->  {
                        log.debug(objectError.toString());
                    }
            );

            return "app/app-add-monument";
        }

        // mock
        MonumentCommand commandSaved = monumentService.saveMonumentCommand(command);

        return "redirect:/app/"  + commandSaved.getId() +"/app-show-monument";
    }


    // we expect the MVC Controller to return back a populated object
    @GetMapping("/app/{id}/update")
    public String updateMonument(@PathVariable String id, Model model) {

        model.addAttribute("monument", monumentService.findMonumentCommandById(new Long(id)));

        return "app/app-add-monument";
    }



    @GetMapping("/app/all")
    public String getAllMonuments(Model model) {
        log.debug("Getting index page");

        model.addAttribute("monuments", monumentService.getMonuments());

        return "redirect:/app/app-search-result";
    }



    @GetMapping("/app/{id}/delete")
    public String deleteMonument(@PathVariable String id) {

        log.debug("Deleting: " + id);

        monumentService.deleteById(new Long(id));

        return "redirect:/app/all";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleMonumentNotFound(Exception e) {

        log.error("Handling monument not found exception");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error404");
        modelAndView.addObject("exc", e);


        return modelAndView;
    }




    /**
     * pre-processes  all the web requests incoming and trims leading&trailing whitespaces.
     * This is done to prevent the situation, where whitespaces are passing validation
     *
     * @param webDataBinder binds data from web request to backing bean
     */

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}
