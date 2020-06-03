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

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user

        model.addAttribute("currentUser", currentUser);// 2. Show current user on form
        model.addAttribute("friends", usersRepo.findAllById(currentUser.getId())); // 2. Show a list of users attached to current user

        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny

        return "users/showConnections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends/edit")
    public String addFriend(@ModelAttribute User friend, Model model) {

        // --------- ADD FRIEND TO USER------------
        model.addAttribute("friend", friend); // 1.Get the friend from form
        List<User> friendToUser = new ArrayList<>(); // 2. Create a list for friends
        friendToUser.add(friend); // 3. add the friend to the list
        friend.setFriendList(friendToUser);


        // --------- ADD USER TO FRIEND-----------
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        List<User> userToFriend = new ArrayList<>(); // 2. Create a list for friends
        userToFriend.add(currentUser); // 3. add the friend to the list
        currentUser.setFriendList(userToFriend); // 2. save the list of friend to users friendList column


        // --------- SAVE TO DB -----------
        usersRepo.save(currentUser); // 3. save the list of users to the current users information
        usersRepo.save(friend); // 3. save the list of users to the current users information

        return "users/showConnections";
    }


    // --------- DELETE FRIEND (POST)------------

    // --------- SEARCH FOR FRIEND (POST)------------


}
