package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoriesController {

    @GetMapping("/stories")
    public String index() {
        return "stories";
    }

}
