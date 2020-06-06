package com.progeny.controllers;

import com.progeny.model.Recording;
import com.progeny.model.Story;
import com.progeny.model.User;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.StoryRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaveRecordingController {

    // --------- INITIALIZE ------------
    private RecordingRepository recordingsRepo;
    private UserRepository usersRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public SaveRecordingController(RecordingRepository recordingsRepo, UserRepository usersRepo) {
        this.recordingsRepo = recordingsRepo;
        this.usersRepo = usersRepo;
    }


    // --------- CREATE RECORDING (GET) ------------
    @GetMapping("/recording/save")
    public String showRegisterForm(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = new User(user);
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("recording", new Recording());

        return "recordings/saveRecording";
    }

    // --------- CREATE RECORDING (POST)------------
    @PostMapping("/recording/save")
    public String create(@ModelAttribute Recording recording, Model model,  @RequestParam long userId) {

        model.addAttribute("userId" , userId);

        recording.setUser(usersRepo.findById(userId));
        System.out.println(usersRepo.findById(userId).getUsername());

        recording.setTimestamp(recording.getTimestamp());

        recordingsRepo.save(recording);

        return "redirect:/profile";
    }
}
