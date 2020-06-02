package com.progeny.controllers;


import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CreateRecordingController {

    private StoryRepository storyRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public CreateRecordingController(StoryRepository stories) {
        this.storyRepo = stories;
    }


    @GetMapping("/recording/create/{id}")
    public String index(@PathVariable long id, Model model) {



        model.addAttribute("currentStory", storyRepo.getOne(id));

        return "recordings/createRecording";
    }

}
