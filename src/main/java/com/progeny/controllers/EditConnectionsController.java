package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditConnectionsController {

    @GetMapping("/connections/edit")
    public String index() {
        return "editConnections";
    }

}
