package com.progeny.controllers;

import com.progeny.model.Story;
import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    //********************* method for taking admin to delete story page ******************************************
    @GetMapping("/story/{id}/delete")
    public String getDeleteStoryForm(@PathVariable long id, Model model){
        Story deleteStory = storyRepo.getOne(id);
        model.addAttribute("story", deleteStory);
        return "stories/deleteStory";
    }


    //******************** method for deleting deleting a story from database ****************
    @PostMapping("/story/{id}/delete")
    public String deletePost(@ModelAttribute Story story){
        Story deleteStory = storyRepo.getOne(story.getId());
        storyRepo.delete(deleteStory);
        return "redirect:/stories";
    }

}
