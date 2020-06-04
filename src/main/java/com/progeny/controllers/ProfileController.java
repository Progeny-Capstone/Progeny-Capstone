package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {


    private UserRepository userRepo;
    private RecordingRepository recordingRepo;
    private GroupRepository groupsRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ProfileController(UserRepository userRepo, RecordingRepository recordingRepo, GroupRepository groupsRepo) {
        this.userRepo = userRepo;
        this.recordingRepo = recordingRepo;
        this.groupsRepo = groupsRepo;
    }


    // ------------ SHOW PROFILE (GET) ---------------
    @GetMapping("/profile")
    public String index(Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get the current user

        model.addAttribute("currentUser", currentUser); // Show current user details on page

        model.addAttribute("friends", userRepo.findAllById(currentUser.getId())); // Show a list of users attached to current user


        model.addAttribute("recordings", recordingRepo.findAll()); // Place all the recordings on the page

        model.addAttribute("groups", groupsRepo.findAll()); // Place all the groups on the page

        return "users/profile";
    }





}
