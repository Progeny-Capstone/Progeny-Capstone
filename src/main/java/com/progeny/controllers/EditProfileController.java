package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProfileController {

    @GetMapping("/profile/edit")
    public String index() {
        return "users/editProfile";
    }

}
