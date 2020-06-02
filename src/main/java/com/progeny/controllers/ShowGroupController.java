package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowGroupController {

    @GetMapping("/group")
    public String index() {
        return "groups/showGroup";
    }

}
