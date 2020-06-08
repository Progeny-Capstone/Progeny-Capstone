package com.progeny.controllers;

import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.isAdmin()){
            model.addAttribute("users", usersRepo.findAll());
            return "admin/adminShowUsers";
        } else{
            return "redirect:/login";
        }
    }


}
