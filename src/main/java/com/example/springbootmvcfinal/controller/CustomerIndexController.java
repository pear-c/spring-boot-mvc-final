package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerIndexController {

    private final InquiryRepository inquiryRepository;

    @GetMapping
    public String viewInquiryList(@RequestParam(value ="category", required = false) InquiryCategory category,
                                  HttpSession session, Model model) {
        Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
        if(loginCustomer == null) {
            return "redirect:/cs/login";
        }

        List<Inquiry> inquiryList = inquiryRepository.findByCustomerId(loginCustomer.getId());
        if(category == null) {
            Collections.sort(inquiryList, (o1, o2) -> {
                return o2.getCreatedAt().compareTo(o1.getCreatedAt());
            });
            model.addAttribute("inquiryList", inquiryList);
        } else { // 분류 검색 시
            List<Inquiry> filtered = new ArrayList<>();
            for (Inquiry inquiry : inquiryList) {
                if(inquiry.getCategory().equals(category)) {
                    filtered.add(inquiry);
                }
            }

            Collections.sort(filtered, (o1, o2) -> {
                return o2.getCreatedAt().compareTo(o1.getCreatedAt());
            });
            model.addAttribute("inquiryList", filtered);
        }

        return "customerPage";
    }
}
