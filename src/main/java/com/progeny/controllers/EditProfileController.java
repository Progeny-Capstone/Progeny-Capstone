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

@Controller
public class EditProfileController {


    // --------- INITIALIZE ------------
    private UserRepository users;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public EditProfileController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- Edit USER (GET) ------------
    @GetMapping("/profile/edit")
    public String index(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        return "users/editProfile";
    }


    // --------- Edit USER (POST)------------
    @PostMapping("/profile/edit")
    public String saveUser(@ModelAttribute User user){

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

//        user.setFirstName();
//        user.setLastName();
//        user.setEmail();
//        user.setProfileImageUrl();
//        user.setLocation();
//        user.setBio();
//        user.setUsername();

        users.save(user);

        return "redirect:/profile";
    }

}
