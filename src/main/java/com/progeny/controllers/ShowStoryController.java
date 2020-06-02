package com.progeny.controllers;

import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShowStoryController {

    // --------- INITIALIZE ------------
    private StoryRepository storyRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ShowStoryController(StoryRepository stories) {
        this.storyRepo = stories;
    }

    // --------- SHOW STORY (GET) ------------
    @GetMapping("/story/{id}")
    public String index(@PathVariable long id, Model model) {

        model.addAttribute("currentStory", storyRepo.getOne(id));

        return "stories/showStory";
    }

}
