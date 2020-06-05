package com.progeny.controllers;

import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowRecordingController {

    // --------- INITIALIZE ------------
    private RecordingRepository recordingRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ShowRecordingController(RecordingRepository recording) {
        this.recordingRepo = recording;
    }

    @GetMapping("/recording/show/{id}")
    public String index(@PathVariable long id, Model model) {

        model.addAttribute("currentRecording", recordingRepo.getOne(id));

        return "recordings/showRecording";
    }

}
