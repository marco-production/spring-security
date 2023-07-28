package com.store.electronic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping("/about-us")
    public ModelAndView aboutUs(){
        ModelAndView mv = new ModelAndView("views/about");
        mv.addObject("currentYear", LocalDate.now().getYear());
        return  mv;
    }

    @GetMapping("/login")
    public String login() {
        return "views/authentication/login";
    }
}
