package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin")
public class CsManagerIndexController {

    private final InquiryRepository inquiryRepository;

    @GetMapping
    public String viewInquiryListWithoutAnswer(Model model) {
        List<Inquiry> inquiryList = inquiryRepository.findAll();
        List<Inquiry> unansweredList = new ArrayList<>();
        for (Inquiry inquiry : inquiryList) {
            if(!inquiry.isAnswered()){
                unansweredList.add(inquiry);
            }
        }
        model.addAttribute("unansweredList", unansweredList);
        return "adminPage";
    }
}
