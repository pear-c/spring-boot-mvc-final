package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/inquiry")
public class InquiryViewController {

    private final InquiryRepository inquiryRepository;

    @GetMapping("/{id}")
    public String viewInquiryDetail(@PathVariable("id") Long id, Model model) {
        Inquiry inquiry = inquiryRepository.findById(id);
        model.addAttribute("inquiry", inquiry);
        return "inquiryDetail";
    }
}
