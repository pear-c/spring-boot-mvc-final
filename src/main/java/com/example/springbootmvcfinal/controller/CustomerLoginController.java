package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.Customer;
import com.example.springbootmvcfinal.domain.Inquiry;
import com.example.springbootmvcfinal.exception.CustomerNotFoundException;
import com.example.springbootmvcfinal.repository.CustomerRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/login")
public class CustomerLoginController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public String login(HttpSession session) {
        if(session.getAttribute("loginCustomer") != null) {
            return "redirect:/cs";
        }

        return "loginForm";
    }

    @PostMapping
    public String doLogin(@RequestParam String id, @RequestParam String password,
                          HttpSession session) {
        if(!customerRepository.matches(id, password)) {
            throw new CustomerNotFoundException();
        }

        Customer customer = customerRepository.findById(id);
        session.setAttribute("loginCustomer", customer);

        return "redirect:/cs";
    }
}
