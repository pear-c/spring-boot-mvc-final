package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerViewControllerTest {
    @Mock
    AnswerRepository answerRepository;
    @Mock
    Model model;

    @InjectMocks
    AnswerViewController controller;

    @Test
    void viewAnswer() {
        Long inquiryId = 1L;
        Answer answer = new Answer(1L, 2L, "답변 드립니다~", "admin", LocalDateTime.now());
        when(answerRepository.findByInquiryId(inquiryId)).thenReturn(answer);

        String view = controller.viewAnswer(inquiryId, model);

        verify(answerRepository).findByInquiryId(inquiryId);
        verify(model).addAttribute("answer", answer);
        assertEquals("answerDetail", view);
    }
}