package com.progeny.controllers;

import com.progeny.model.Group;
import com.progeny.model.Story;
import com.progeny.repositories.GroupRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateGroupController {

    // --------- INITIALIZE ------------
    private GroupRepository groupsRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public CreateGroupController(GroupRepository groups) {
        this.groupsRepo = groups;
    }


    // --------- CREATE GROUP (GET) ------------
    @GetMapping("/group/create")
    public String showCreateGroup(Model model) {

        model.addAttribute("newGroup", new Group());

        return "groups/createGroup"; // HTML PATH
    }

    // --------- CREATE GROUP (POST)------------
    @PostMapping("/group/create")
    public String createGroupForm(@ModelAttribute Group newGroup){

        groupsRepo.save(newGroup);

        return "redirect:/profile"; // REDIRECT: URL PATH
    }

}
