package com.progeny.controllers;

import com.progeny.model.Recording;
import com.progeny.model.Story;
import com.progeny.model.User;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveRecordingController {

    // --------- INITIALIZE ------------
    private RecordingRepository recordings;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public SaveRecordingController(RecordingRepository recordings) {
        this.recordings = recordings;
    }


    // --------- CREATE RECORDING (GET) ------------
    @GetMapping("/recording/save")
    public String showRegisterForm(Model model){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        model.addAttribute("currentUserId" , user.getId());
        model.addAttribute("recording", new Recording());
        return "recordings/saveRecording";
    }

    //     --------- CREATE RECORDING (POST)------------
    @PostMapping("/recording/save")
    public Recording create(@ModelAttribute Recording recording) {

        recordings.save(recording);

        return recording;
    }
}
