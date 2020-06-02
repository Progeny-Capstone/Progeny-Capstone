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

    private StoryRepository storyRepo;

    public ShowStoryController(StoryRepository stories) {
        this.storyRepo = stories;
    }

//    @GetMapping("/story/{id}")
//    public String index() {
//        return "stories/showStory";
//    }

    @GetMapping("/story/{id}")
    public String index(@PathVariable long id, Model model) {
        model.addAttribute("currentStory", storyRepo.getOne(id));

        String storyTitle = storyRepo.getStoriesById(id).getTitle();
        model.addAttribute("storyTitle", storyTitle);
        String storySummary = storyRepo.getStoriesById(id).getSummary();
        model.addAttribute("storySummary", storySummary);
        String storyFile = storyRepo.getStoriesById(id).getStoryFile();
        model.addAttribute("storyFile", storyFile);

        return "stories/showStory";
    }

}
