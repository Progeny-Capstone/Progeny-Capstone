package com.progeny.controllers;

import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoriesController {

    private StoryRepository storyRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public StoriesController(StoryRepository storyRepo) {
        this.storyRepo = storyRepo;
    }

    @GetMapping("/stories")
    public String index(Model model) {

        model.addAttribute("stories", storyRepo.findAll()); // Place all the groups on the page
        return "stories";
    }

}
