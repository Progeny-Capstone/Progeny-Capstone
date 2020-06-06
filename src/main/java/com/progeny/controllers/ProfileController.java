package com.progeny.controllers;

import com.progeny.model.Recording;
import com.progeny.model.User;
import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.RecordingRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        // ------------ CURRENT USER ---------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get the current user
        User currentUser = new User(user);
        model.addAttribute("currentUser", currentUser); // Show current user details on page


        // ------------ GET THE CURRENT USERS FRIENDS ---------------
        model.addAttribute("friends", currentUser.getFriends()); // Show a list of users attached to current user

        // ------------ IF NO RECORDINGS LIST ---------------
        if(user.getRecordings() == null){ // if there is no friends list -->

            List<Recording> userRecordings = new ArrayList<>(); // 1. Make a new recordings list
            user.setRecordings(userRecordings); // 2. give the new recordings list to User

        }

        // ------------ RECORDING LIST ---------------
        model.addAttribute("recordings", recordingRepo.getAllByUserId(currentUser.getId()));// 3. Show current recordings for user on profile page

        return "users/profile";
    }





}
