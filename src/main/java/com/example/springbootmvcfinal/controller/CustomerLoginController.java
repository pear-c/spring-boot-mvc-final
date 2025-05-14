package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.exception.CustomerNotFoundException;
import com.example.springbootmvcfinal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/login")
public class CustomerLoginController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public String login() {
        return "loginForm";
    }

    @PostMapping
    public String doLogin(@RequestParam String id, @RequestParam String password, Model model) {
        if(!customerRepository.matches(id, password)) {
            throw new CustomerNotFoundException();
        }
        return "customerPage";
    }
}
