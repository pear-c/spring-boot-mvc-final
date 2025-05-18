package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.domain.answer.AnswerRegisterRequest;
import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.exception.ValidationFailedException;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.CsManagerRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin/answer")
public class CsManagerAnswerController {

    private final InquiryRepository inquiryRepository;
    private final AttachmentRepository attachmentRepository;
    private final AnswerRepository answerRepository;

    @GetMapping
    public String viewAnswerForm(@RequestParam("id") Long inquiryId, Model model) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId);
        model.addAttribute("inquiry", inquiry);

        List<Attachment> attachments = attachmentRepository.findByInquiryId(inquiryId);
        model.addAttribute("attachments", attachments);

        return "adminAnswerForm";
    }

    @PostMapping
    public String answerInquiry(@RequestParam("id") Long inquiryId,
                                @Valid @ModelAttribute AnswerRegisterRequest registerRequest,
                                BindingResult bindingResult,
                                HttpSession session) {

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        CsManager csManager = (CsManager) session.getAttribute("loginAdmin");
        Answer answer = new Answer(
                answerRepository.increaseAndGetAnswerId(),
                inquiryId,
                registerRequest.getContent(),
                csManager.getName(),
                LocalDateTime.now()
        );
        answerRepository.save(answer);

        Inquiry inquiry = inquiryRepository.findById(inquiryId);
        inquiry.setAnswered(true);
        inquiryRepository.save(inquiry);

        return "redirect:/cs/admin";
    }
}
