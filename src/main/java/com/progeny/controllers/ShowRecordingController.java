package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowRecordingController {

    @GetMapping("/recording/show")
    public String index() {
        return "recordings/showRecording";
    }

}
