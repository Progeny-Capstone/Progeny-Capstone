package com.progeny.controllers;


import com.progeny.model.User;
import com.progeny.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminDeleteUserController {


    // --------- INITIALIZE ------------
    private UserRepository usersRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public AdminDeleteUserController(UserRepository usersRepo) {
        this.usersRepo = usersRepo;
    }



    //******************** method for taking user to delete profile warning page **************
    @GetMapping("/admin/delete/{name}")
    public String getDeleteForm(@PathVariable String name, Model model){
        User deleteProfile = usersRepo.findByUsername(name);
//        deleteProfile.getFriends();
        model.addAttribute("user", deleteProfile);

        return "admin/admin-delete-user";
    }

    //******************** method for deleting deleting a user profile from database ****************
    @PostMapping("/admin/delete")
    public String deleteProfile(@RequestParam long userId){
        User deleteProfile = usersRepo.getOne(userId);
        usersRepo.delete(deleteProfile);
        return "redirect:/admin";
    }

}
