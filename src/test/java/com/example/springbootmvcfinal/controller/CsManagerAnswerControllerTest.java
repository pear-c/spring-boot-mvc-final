package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.domain.answer.AnswerRegisterRequest;
import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.exception.ValidationFailedException;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsManagerAnswerControllerTest {
    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    AttachmentRepository attachmentRepository;
    @Mock
    AnswerRepository answerRepository;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    @Mock
    HttpSession session;

    @InjectMocks
    CsManagerAnswerController controller;

    @Test
    void viewAnswerForm() {
        Long inquiryId = 1L;
        Inquiry inquiry = new Inquiry(inquiryId, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Attachment attachment = new Attachment(1L,1L, "tmp_image.png", "tmp_image.png", "image/png", 10000L, "/images");
        List<Attachment> attachments = List.of(attachment);

        when(inquiryRepository.findById(inquiryId)).thenReturn(inquiry);
        when(attachmentRepository.findByInquiryId(inquiryId)).thenReturn(attachments);

        String view = controller.viewAnswerForm(inquiryId, model);

        verify(model).addAttribute("inquiry", inquiry);
        verify(model).addAttribute("attachments", attachments);
        assertEquals("adminAnswerForm", view);
    }

    @Test
    void checkValidException() {
        when(bindingResult.hasErrors()).thenReturn(true);

        AnswerRegisterRequest request = new AnswerRegisterRequest("답변내용");

        assertThrows(ValidationFailedException.class, () -> {
            controller.answerInquiry(1L, request, bindingResult, session);
        });
    }

    @Test
    void answerInquiry() {
        Long inquiryId = 1L;

        when(bindingResult.hasErrors()).thenReturn(false);
        when(answerRepository.increaseAndGetAnswerId()).thenReturn(1L);

        CsManager manager = new CsManager("admin", "12345", "CS담당자", "고객관리팀");
        when(session.getAttribute("loginAdmin")).thenReturn(manager);

        Inquiry inquiry = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        when(inquiryRepository.findById(inquiryId)).thenReturn(inquiry);

        AnswerRegisterRequest request = new AnswerRegisterRequest("답변내용");

        String view = controller.answerInquiry(inquiryId, request, bindingResult, session);

        verify(answerRepository).save(any(Answer.class));
        verify(inquiryRepository).save(inquiry);
        assertTrue(inquiry.isAnswered());
        assertEquals("redirect:/cs/admin", view);
    }
}