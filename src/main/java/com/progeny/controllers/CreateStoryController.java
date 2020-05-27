package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateStoryController {

    @GetMapping("/story/create")
    public String index() {
        return "stories/createStory";
    }

}
