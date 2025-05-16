package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin/answer")
public class CsManagerAnswerController {

    private final InquiryRepository inquiryRepository;

    @GetMapping
    public String viewAnswerForm(@RequestParam("id") Long inquiryId, Model model) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "adminAnswerForm";
    }
}
