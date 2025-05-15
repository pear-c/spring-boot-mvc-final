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
    private final InquiryRepository inquiryRepository;

    @GetMapping
    public String login() {
        return "loginForm";
    }

    @PostMapping
    public String doLogin(@RequestParam String id, @RequestParam String password,
                          HttpSession session, Model model) {
        if(!customerRepository.matches(id, password)) {
            throw new CustomerNotFoundException();
        }

        Customer customer = customerRepository.findById(id);
        session.setAttribute("loginCustomer", customer);

        List<Inquiry> inquiryList = inquiryRepository.findByCustomerId(id);
        model.addAttribute("inquiryList", inquiryList);

        return "customerPage";
    }
}
