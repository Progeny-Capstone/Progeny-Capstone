package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditStoryController {

    @GetMapping("/story/edit")
    public String index() {
        return "editstory";
    }

}
