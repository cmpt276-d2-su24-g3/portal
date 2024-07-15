package com.bhavjit.cmpt276.portal.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavjit.cmpt276.portal.services.LogoutService;

@RestController
@RequestMapping(value = {"/test"})
public class TestController {

    @GetMapping("/")
    public String home(Model model) {
        return "page";
    }

    @GetMapping("/authenticate")
    public String authenticate() {
        return "logged in";
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(value = "refresh_token", defaultValue = "none") String token) {
        System.out.println("TOKEN:");
        System.out.println(token);
        if(token == "none") return "error"; // TODO: implement error handling?
        LogoutService.logUserOut(token);
        return "logged in";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

}
