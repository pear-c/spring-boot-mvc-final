package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InquiryViewControllerTest {

    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    AttachmentRepository attachmentRepository;
    @Mock
    Model model;

    @InjectMocks
    InquiryViewController controller;

    @Test
    void viewInquiryDetail() {
        Long inquiryId = 1L;
        Inquiry inquiry = new Inquiry(
                inquiryId,
                "테스트 제목",
                InquiryCategory.PRAISE,
                "테스트 내용",
                LocalDateTime.now(),
                "testId",
                false
        );

        List<Attachment> attachments = List.of(
                new Attachment(1L, inquiryId, "a.png", "a.png", "image/png", 1000L, "/images")
        );

        when(inquiryRepository.findById(inquiryId)).thenReturn(inquiry);
        when(attachmentRepository.findByInquiryId(inquiryId)).thenReturn(attachments);

        String view = controller.viewInquiryDetail(inquiryId, model);

        verify(inquiryRepository).findById(inquiryId);
        verify(attachmentRepository).findByInquiryId(inquiryId);
        verify(model).addAttribute("inquiry", inquiry);
        verify(model).addAttribute("attachments", attachments);

        assertEquals("inquiryDetail", view);
    }
}