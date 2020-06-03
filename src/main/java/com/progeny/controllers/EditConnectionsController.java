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
import org.springframework.web.bind.annotation.RequestParam;

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

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user

        model.addAttribute("currentUser", currentUser);// 2. Show current user on form
        model.addAttribute("friends", usersRepo.findAllById(currentUser.getId())); // 2. Show a list of users attached to current user

        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny

        return "users/showConnections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends/edit")
    public String addFriend(@RequestParam long friendId, Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user

        User friend = usersRepo.getUserById(friendId);

        System.out.println(currentUser.getFirstName());
        System.out.println(friend.getFirstName());

        if(friend.getFriends() == null){ // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            friend.setFriends(newFriends); // 2. give the new friends list to User

        }
        if(currentUser.getFriends() == null){ // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            currentUser.setFriends(newFriends); // 2. give the new friends list to User

        }

        // --------- ADD USER TO FRIEND-----------
        currentUser.getFriends().add(friend);

        System.out.println(currentUser.getFriends().get(0).getUsername());

        // --------- ADD FRIEND TO USER------------
        friend.getFriends().add(currentUser);

        System.out.println(friend.getFriends().get(0).getUsername());

        // --------- SAVE TO DB -----------
        usersRepo.save(currentUser); // 3. save the list of users to the current users information
        usersRepo.save(friend); // 3. save the list of users to the current users information

        return "users/showConnections";
    }


    // --------- DELETE FRIEND (POST)------------

    // --------- SEARCH FOR FRIEND (POST)------------


}
