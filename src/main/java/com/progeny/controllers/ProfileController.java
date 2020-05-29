package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
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



    @GetMapping("/profile/{id}")
    public String index(@PathVariable long id, Model model, ModelMap modelMap) {

        model.addAttribute("currentUser", userRepo.getOne(id));
        String currentName = userRepo.findById(id).getFirstName();


        modelMap.addAttribute("currentName", currentName);


        return "users/profile";
    }


//    @PostMapping("/profile")
//    public String addUser(Model model, User user) {
//        model.addAttribute(user);
//        return "redirect:/profile"
//    }



}
