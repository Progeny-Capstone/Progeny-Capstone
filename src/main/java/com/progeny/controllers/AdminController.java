package com.progeny.controllers;

import com.progeny.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    private UserRepository usersRepo;

    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public AdminController(UserRepository usersRepo) {
        this.usersRepo = usersRepo;

    }

    @GetMapping("/admin")
    public String getPosts(Model model) {
        model.addAttribute("users", usersRepo.findAll());
        return "admin/adminShowUsers";
    }


}
