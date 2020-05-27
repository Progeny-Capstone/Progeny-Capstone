package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaveRecordingController {

    @GetMapping("/recording/save")
    public String index() {
        return "recordings/saveRecording";
    }

}
