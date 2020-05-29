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
public class UserController {

    // --------- INITIALIZE ------------
    private UserRepository users;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- CREATE USER (GET) ------------
    @GetMapping("/register")
    public String showRegisterForm(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("user", new User());
        return "users/register";
    }

    // --------- CREATE USER (POST)------------
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

}
