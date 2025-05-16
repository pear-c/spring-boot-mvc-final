package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.exception.CustomerNotFoundException;
import com.example.springbootmvcfinal.repository.CsManagerRepository;
import com.example.springbootmvcfinal.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/login")
public class CustomerLoginController {

    private final CustomerRepository customerRepository;
    private final CsManagerRepository csManagerRepository;

    @GetMapping
    public String login(HttpSession session) {
        if(session.getAttribute("loginCustomer") != null) {
            return "redirect:/cs";
        } else if(session.getAttribute("loginAdmin") != null) {
            return "redirect:/cs/admin";
        }

        return "loginForm";
    }

    @PostMapping
    public String doLogin(@RequestParam String id, @RequestParam String password,
                          HttpSession session) {

        if(id.equals("admin")) {
            CsManager csManager = csManagerRepository.findById("admin");
            session.setAttribute("loginAdmin", csManager);
            return "redirect:/cs/admin";
        }

        if(!customerRepository.matches(id, password)) {
            throw new CustomerNotFoundException();
        }
        Customer customer = customerRepository.findById(id);
        session.setAttribute("loginCustomer", customer);
        return "redirect:/cs";
    }
}
