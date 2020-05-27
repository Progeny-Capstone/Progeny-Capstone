package com.progeny.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateRecordingController {

    @GetMapping("/recording/create")
    public String index() {
        return "recordings/createRecording";
    }

}
