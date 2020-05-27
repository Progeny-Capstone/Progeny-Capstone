package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowConnectionsController {

    @GetMapping("/connections")
    public String index() {
        return "users/showConnections";
    }

}
