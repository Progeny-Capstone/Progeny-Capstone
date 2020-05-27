package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    // --------- INITIALIZE ------------

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------


    // --------- HOME PAGE DISPLAY (GET) ------------
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
