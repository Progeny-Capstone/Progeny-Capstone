package com.progeny.controllers;

import com.progeny.model.Recording;
import com.progeny.model.Story;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecordingController {

    // --------- INITIALIZE ------------
    private RecordingRepository recordingRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public RecordingController(RecordingRepository recording) {
        this.recordingRepo = recording;
    }

    @GetMapping("/recording/show/{id}")
    public String index(@PathVariable long id, Model model) {

        model.addAttribute("currentRecording", recordingRepo.getOne(id));

        return "recordings/showRecording";
    }



    // ---------- DELETE RECORDING (GET) ------------
    @GetMapping("/recording/delete/{id}")
    public String getDeleteRecordingForm(@PathVariable long id, Model model){
        Recording deleteRecording = recordingRepo.getOne(id);
        model.addAttribute("recording", deleteRecording);
        return "recordings/deleteRecording";
    }


    // ---------- DELETE RECORDING (POST) ------------
    @PostMapping("/recording/delete/{id}")
    public String deleteRecording(@ModelAttribute Recording recording){
        Recording deleteRecording = recordingRepo.getOne(recording.getId());
        recordingRepo.delete(deleteRecording);
        return "redirect:/profile";
    }

}
