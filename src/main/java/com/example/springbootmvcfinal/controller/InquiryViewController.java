package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/inquiry")
public class InquiryViewController {

    private final InquiryRepository inquiryRepository;
    private final AttachmentRepository attachmentRepository;

    @GetMapping("/{id}")
    public String viewInquiryDetail(@PathVariable("id") Long id, Model model) {
        Inquiry inquiry = inquiryRepository.findById(id);
        model.addAttribute("inquiry", inquiry);

        List<Attachment> attachments = attachmentRepository.findByInquiryId(id);
        model.addAttribute("attachments", attachments);

        return "inquiryDetail";
    }
}
