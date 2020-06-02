package com.progeny.controllers;

import com.progeny.model.Group;
import com.progeny.model.Story;
import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.StoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditStoryController {

    // --------- INITIALIZE ------------
    private StoryRepository storiesRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public EditStoryController(StoryRepository storiesRepo) {
        this.storiesRepo = storiesRepo;
    }

    // --------- EDIT STORY (GET) ------------
    @GetMapping("/story/{id}/edit")
    public String index(@PathVariable long id, Model model) {

        Story editStory = storiesRepo.getStoriesById(id); // get the story

        model.addAttribute("story", editStory); // show the story

        return "stories/editStory";
    }

    // --------- EDIT STORY (POST)------------
    @PostMapping("/story/{id}/edit")
    public String createGroupForm(@PathVariable long id, @ModelAttribute Story editStory){

        Story currentStoryEdits = storiesRepo.getStoriesById(id); // get the id of the Group

        currentStoryEdits.setAuthorFirstName(editStory.getAuthorFirstName());
        currentStoryEdits.setAuthorLastName(editStory.getAuthorLastName());
        currentStoryEdits.setCoverImageUrl(editStory.getCoverImageUrl());
        currentStoryEdits.setPublishedYear(editStory.getPublishedYear());
        currentStoryEdits.setPublisher(editStory.getPublisher());
        currentStoryEdits.setTitle(editStory.getTitle());
        currentStoryEdits.setSummary(editStory.getSummary());
        currentStoryEdits.setStoryFile(editStory.getStoryFile());

        storiesRepo.save(editStory);

        return "redirect:/story/{id}";
    }

}
