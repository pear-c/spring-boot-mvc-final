package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerIndexControllerTest {
    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    HttpSession session;
    @Mock
    Model model;

    @InjectMocks
    CustomerIndexController controller;


    @Test
    void checkRedirectionWithoutLogin() {
        when(session.getAttribute("loginCustomer")).thenReturn(null);

        String view = controller.viewInquiryList(null, session, model);

        assertEquals("redirect:/cs/login", view);
    }

    @Test
    void viewInquiryList() {
        Customer customer = new Customer("test", "12345", "홍길동");
        when(session.getAttribute("loginCustomer")).thenReturn(customer);

        Inquiry inquiry1 = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Inquiry inquiry2 = new Inquiry(2L, "칭찬합니다.", InquiryCategory.PRAISE, "상품 아주 마음에 들어요", LocalDateTime.now(), "test", true);
        List<Inquiry> inquiryList = Arrays.asList(inquiry1, inquiry2);
        when(inquiryRepository.findByCustomerId("test")).thenReturn(inquiryList);

        String view = controller.viewInquiryList(null, session, model);

        verify(model).addAttribute("inquiryList", inquiryList);
        assertEquals("customerPage", view);
    }

    @Test
    void viewInquiryListWithCategory() {
        Customer customer = new Customer("test", "12345", "홍길동");
        when(session.getAttribute("loginCustomer")).thenReturn(customer);

        Inquiry inquiry1 = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Inquiry inquiry2 = new Inquiry(2L, "칭찬합니다.", InquiryCategory.PRAISE, "상품 아주 마음에 들어요", LocalDateTime.now(), "test", true);
        List<Inquiry> inquiryList = Arrays.asList(inquiry1, inquiry2);
        when(inquiryRepository.findByCustomerId("test")).thenReturn(inquiryList);

        String view = controller.viewInquiryList(InquiryCategory.REFUND, session, model);

        ArgumentCaptor<List<Inquiry>> captor = ArgumentCaptor.forClass(List.class);
        verify(model).addAttribute(eq("inquiryList"), captor.capture());

        List<Inquiry> resultList = captor.getValue();
        assertEquals(1, resultList.size());
        assertEquals(InquiryCategory.REFUND, resultList.get(0).getCategory());

        assertEquals("customerPage", view);
    }
}