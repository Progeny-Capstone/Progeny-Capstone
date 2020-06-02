package com.progeny.controllers;

import com.progeny.repositories.GroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowGroupController {

    // --------- INITIALIZE ------------
    private GroupRepository groupsRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ShowGroupController(GroupRepository groups) {
        this.groupsRepo = groups;
    }


    @GetMapping("/group/{id}")
    public String index(@PathVariable long id, Model model) {

        model.addAttribute("currentGroup", groupsRepo.getOne(id)); // find the ad by its id

        String groupName = groupsRepo.getGroupById(id).getName();
        model.addAttribute("groupName", groupName);

        String groupDescription = groupsRepo.getGroupById(id).getDescription();
        model.addAttribute("groupDescription", groupDescription);

        return "groups/showGroup"; // HTML PATH
    }

}
