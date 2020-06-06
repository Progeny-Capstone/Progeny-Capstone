package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConnectionsController {

    // --------- INITIALIZE ------------
    private UserRepository usersRepo;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ConnectionsController(UserRepository usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- SHOW (GET) ------------
    @GetMapping("/profile/friends")
    public String showFriends(Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form

        model.addAttribute("friends", currentUser.getFriends()); // 2. Show a list of users attached to current user

        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny

        return "users/connections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends")
    public String addFriend(@RequestParam long friendId, Model model) {

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 3. Show current user on page

        // ------------- GET FRIEND FROM FORM SENT -------------
        User friend = usersRepo.getUserById(friendId);
        model.addAttribute("friend", friend);// 3. Show current user on page

        // ------------- CHECK FOR FRIEND LIST(s) -------------
        if(friend.getFriends() == null){ // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            friend.setFriends(newFriends); // 2. give the new friends list to User

        }
        if(currentUser.getFriends() == null){ // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            currentUser.setFriends(newFriends); // 2. give the new friends list to User

        }

        // --------- ADD USERS TO EACH OTHERS FRIENDS LIST------------
        friend.getFriends().add(currentUser);
        friend.setFriends(friend.getFriends());

        currentUser.getFriends().add(friend);
        currentUser.setFriends(currentUser.getFriends());

        // --------- SAVE TO DB -----------
        usersRepo.save(currentUser); // 3. save the list of users to the current users information
        usersRepo.save(friend); // 3. save the list of users to the current users information


        // --------- REDISPLAY THE GET FRIEND INFORMATION AFTER POST -----------
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form
        model.addAttribute("friends", currentUser.getFriends()); // 2. Show a list of users attached to current user
        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny


        return "users/connections";
    }


    // --------- DELETE FRIEND (POST)------------

    // --------- SEARCH FOR FRIEND (POST)------------


}