package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;

import java.util.List;

public interface InquiryRepository {
    List<Inquiry> findByCustomerId(String customerId);
    Inquiry findById(Long id);
    void save(Inquiry inquiry);
    Long increaseAndGetInquiryId();
}
