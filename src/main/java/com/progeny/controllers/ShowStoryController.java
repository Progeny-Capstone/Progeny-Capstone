package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowStoryController {

    @GetMapping("/story")
    public String index() {
        return "stories/showStory";
    }

}