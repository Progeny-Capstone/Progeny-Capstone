package com.progeny.controllers;

import com.progeny.model.Group;
import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditProfileController {


    // --------- INITIALIZE ------------
    private UserRepository usersRepo;
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public EditProfileController(UserRepository usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- Edit USER (GET) ------------
    @GetMapping("/profile/edit/{name}")
    public String index(@PathVariable String name, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        return "users/editProfile";
    }


    // --------- Edit USER (POST)------------
    @PostMapping("/profile/edit/{name}")
    public String saveUser(@PathVariable String name, @ModelAttribute User user){

        User currentUser = usersRepo.findByUsername(name);
        System.out.println(currentUser);

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setProfileImageUrl(user.getProfileImageUrl());
        currentUser.setLocation(user.getLocation());
        currentUser.setBio(user.getBio());
        currentUser.setUsername(user.getUsername());

        usersRepo.save(currentUser);

        return "redirect:/profile";
    }

    //******************** method for deleting deleting a story from database ****************
    @PostMapping("/profile/edit/{name}")
    public String deleteProfile(@ModelAttribute User user){
        User deleteProfile = usersRepo.getOne(user.getId());
        usersRepo.delete(deleteProfile);
        return "redirect:/";
    }

}
