package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditGroupController {

    @GetMapping("/group/edit")
    public String index() {
        return "editGroup";
    }

}
