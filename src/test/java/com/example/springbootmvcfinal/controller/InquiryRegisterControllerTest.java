package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.domain.inquiry.InquiryRegisterRequest;
import com.example.springbootmvcfinal.exception.ValidationFailedException;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InquiryRegisterControllerTest {
    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    AttachmentRepository attachmentRepository;
    @Mock
    HttpSession session;
    @Mock
    BindingResult bindingResult;
    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    InquiryRegisterController controller;

    @Test
    void viewInquiryRegisterForm() {
        assertEquals("inquiryRegister", controller.inquiryRegisterForm());
    }

    @Test
    void checkValidException() {
        when(bindingResult.hasErrors()).thenReturn(true);

        InquiryRegisterRequest request = new InquiryRegisterRequest("제목", InquiryCategory.REFUND, "내용", List.of());

        assertThrows(ValidationFailedException.class, () -> {
            controller.inquiryRegister(request, bindingResult, session);
        });
    }


    @Test
    void inquiryRegister() throws IOException {
        Customer customer = new Customer("test", "12345", "홍길동");
        when(session.getAttribute("loginCustomer")).thenReturn(customer);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(inquiryRepository.increaseAndGetInquiryId()).thenReturn(1L);
        when(attachmentRepository.increaseAndGetAttachmentId()).thenReturn(1L);

        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("file.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        when(multipartFile.getSize()).thenReturn(10000L);

        InquiryRegisterRequest request = new InquiryRegisterRequest("제목", InquiryCategory.PRAISE, "내용", List.of(multipartFile));

        doNothing().when(multipartFile).transferTo(any(Path.class));

        String view = controller.inquiryRegister(request, bindingResult, session);

        verify(inquiryRepository).save(any(Inquiry.class));
        verify(attachmentRepository).save(any(Attachment.class));
        verify(multipartFile).transferTo(any(Path.class));

        assertEquals("redirect:/cs", view);
    }
}