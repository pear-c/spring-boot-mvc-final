package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {
    List<Inquiry> findByCustomerId(String customerId);
}
