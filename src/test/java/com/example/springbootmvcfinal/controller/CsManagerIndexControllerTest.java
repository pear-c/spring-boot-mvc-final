package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.repository.InquiryRepository;
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
class CsManagerIndexControllerTest {
    @Mock
    InquiryRepository inquiryRepository;
    @Mock
    Model model;

    @InjectMocks
    CsManagerIndexController controller;


    @Test
    void viewInquiryListWithoutAnswer() {
        Inquiry inquiry1 = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Inquiry inquiry2 = new Inquiry(2L, "칭찬합니다.", InquiryCategory.PRAISE, "상품 아주 마음에 들어요", LocalDateTime.now(), "test", true);
        List<Inquiry> inquiryList = Arrays.asList(inquiry1, inquiry2);
        when(inquiryRepository.findAll()).thenReturn(inquiryList);

        String view = controller.viewInquiryListWithoutAnswer(model);

        ArgumentCaptor<List<Inquiry>> captor = ArgumentCaptor.forClass(List.class);
        verify(model).addAttribute(eq("unansweredList"), captor.capture());

        List<Inquiry> filteredList = captor.getValue();
        assertEquals(1, filteredList.size());
        assertEquals(inquiry1, filteredList.get(0));

        assertEquals("adminPage", view);
    }
}