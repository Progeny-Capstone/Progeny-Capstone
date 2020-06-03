package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EditConnectionsController {

    // --------- INITIALIZE ------------
    private UserRepository usersRepo;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public EditConnectionsController(UserRepository usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- SHOW (GET) ------------
    @GetMapping("/profile/friends/edit")
    public String showFriends(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 2. Get the current loggedin User
        model.addAttribute("friends", usersRepo.findAllById(user.getId())); // 3. Show a list of users attached to current user

        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny

        return "users/showConnections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends/edit")
    public String addFriend(@ModelAttribute User friend, Model model, HttpSession session) {

        model.addAttribute("friend", friend); // 1.Get the friend from form
        List<User> friends = new ArrayList<>(); // 2. Create a list for friends
        friends.add(friend); // 3. add the friend to the list
        session.setAttribute("friends", friends); // 4. Keep list alive for session?

        return "redirect:/profile";
    }


    // --------- DELETE FRIEND (POST)------------

    // --------- SEARCH FOR FRIEND (POST)------------


}
