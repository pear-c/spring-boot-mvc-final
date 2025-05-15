package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerIndexController {

    private final InquiryRepository inquiryRepository;

    @GetMapping
    public String viewInquiryList(HttpSession session, Model model) {
        Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
        if(loginCustomer == null) {
            return "redirect:/cs/login";
        }

        List<Inquiry> inquiryList = inquiryRepository.findByCustomerId(loginCustomer.getId());
        model.addAttribute("inquiryList", inquiryList);

        return "customerPage";
    }
}
