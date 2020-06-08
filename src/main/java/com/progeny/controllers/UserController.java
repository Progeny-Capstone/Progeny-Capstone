package com.progeny.controllers;


import com.progeny.model.Story;
import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    // --------- INITIALIZE ------------
    private UserRepository usersRepo;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public UserController(UserRepository usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- CREATE USER (GET) ------------
    @GetMapping("/register")
    public String showRegisterForm(Model model){




        model.addAttribute("user", new User());
        return "users/register";
    }

    // --------- CREATE USER (POST)------------
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @RequestParam String filestackUrl){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setProfileImageUrl(filestackUrl);
        usersRepo.save(user);
        return "redirect:/login";
    }




}
