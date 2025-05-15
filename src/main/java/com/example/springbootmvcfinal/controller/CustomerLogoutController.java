package com.example.springbootmvcfinal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class CustomerLogoutController {

    @GetMapping
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cs/login";
    }
}
