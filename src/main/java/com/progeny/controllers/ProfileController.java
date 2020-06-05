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

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get the current user

        model.addAttribute("currentUser", currentUser); // Show current user details on page

        model.addAttribute("friends", currentUser.getFriends()); // Show a list of users attached to current user



        User user = new User(currentUser); // 2. set new user to user ^


        System.out.println(user.getFirstName());


        if(user.getRecordings() == null){ // if there is no friends list -->

            List<Recording> userRecordings = new ArrayList<>(); // 1. Make a new friends list
            user.setRecordings(userRecordings); // 2. give the new friends list to User

        }

        long currentUserId = user.getId();
        System.out.println(currentUserId);


        model.addAttribute("recordings", user.getRecordings());// 3. Show current recordings for user on profile page

        System.out.println(user.getRecordings());


        model.addAttribute("groups", groupsRepo.findAll()); // Place all the groups on the page

        return "users/profile";
    }





}
