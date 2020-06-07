package com.progeny.controllers;

import com.progeny.model.Recording;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EditRecordingController {


    // --------- INITIALIZE ------------
    private RecordingRepository recordingRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public EditRecordingController(RecordingRepository recordingRepo) {
        this.recordingRepo = recordingRepo;
    }



    // ---------- EDIT RECORDING (GET) ------------
    @GetMapping("/recording/edit/{id}")
    public String editRecordingForm(@PathVariable long id, Model model) {

        Recording editRecording = recordingRepo.getOne(id);

        model.addAttribute("recording", editRecording);

        return "recordings/editRecording";
    }


    // --------- EDIT RECORDING (POST)------------
    @PostMapping("/recording/edit/{id}")
    public String editRecording(@PathVariable long id, @ModelAttribute Recording editRecording){
        Recording recordingEdits = recordingRepo.getOne(id);
        recordingEdits.setTitle(editRecording.getTitle());
        recordingRepo.save(recordingEdits);

        return "redirect:/recording/show/{id}";
    }


}
