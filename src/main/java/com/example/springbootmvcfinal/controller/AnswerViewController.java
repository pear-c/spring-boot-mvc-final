package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AnswerViewController {

    private final AnswerRepository answerRepository;

    @GetMapping("/cs/inquiry/{id}/answer")
    public String viewAnswer(@PathVariable("id") Long inquiryId, Model model) {
        Answer answer = answerRepository.findByInquiryId(inquiryId);
        model.addAttribute("answer", answer);
        return "answerDetail";
    }
}
