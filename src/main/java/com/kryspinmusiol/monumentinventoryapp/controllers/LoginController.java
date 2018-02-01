package com.kryspinmusiol.monumentinventoryapp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController {

    //login form
    @RequestMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    // login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {

        model.addAttribute("loginError", true);

        return "login";
    }
}
