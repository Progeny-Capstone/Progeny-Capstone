package com.progeny.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    // ---------- SHOW LOGIN FORM (GET) ----------
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

}
