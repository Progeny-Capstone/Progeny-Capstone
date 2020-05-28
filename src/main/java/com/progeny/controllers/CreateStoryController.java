package com.progeny.controllers;

import com.progeny.model.Story;
import com.progeny.model.User;
import com.progeny.repositories.StoryRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateStoryController {


    // --------- INITIALIZE ------------
    private StoryRepository stories;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public CreateStoryController(StoryRepository stories) {
        this.stories = stories;
    }


    // --------- CREATE STORY (GET) ------------
    @GetMapping("/story/create")
    public String showRegisterForm(Model model){
        model.addAttribute("story", new Story());
        return "stories/createStory";
    }

    // --------- CREATE STORY (POST)------------
    @PostMapping("/story/create")
    public String saveUser(@ModelAttribute Story story){
        stories.save(story);
        return "redirect:/login";
    }


}
