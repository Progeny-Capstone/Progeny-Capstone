package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {


    private UserRepository userRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ProfileController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }



    @GetMapping("/profile")
    public String index(Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currentUser", currentUser);

        return "users/profile";
    }


//    @PostMapping("/profile")
//    public String addUser(Model model, User user) {
//        model.addAttribute(user);
//        return "redirect:/profile"
//    }



}
