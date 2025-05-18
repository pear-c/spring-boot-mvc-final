package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private static final Map<Long, Inquiry> inquiryMap = new HashMap<>();

    @Override
    public List<Inquiry> findByCustomerId(String customerId) {
        List<Inquiry> inquiryList = new ArrayList<>();
        for (Map.Entry<Long, Inquiry> entry : inquiryMap.entrySet()) {
            if(entry.getValue().getCustomerId().equals(customerId)){
                inquiryList.add(entry.getValue());
            }
        }
        return inquiryList;
    }

    @Override
    public List<Inquiry> findAll() {
        return new ArrayList<>(inquiryMap.values());
    }

    @Override
    public Inquiry findById(Long id) {
        return inquiryMap.get(id);
    }

    @Override
    public void save(Inquiry inquiry) {
        inquiryMap.put(inquiry.getId(), inquiry);
    }

    @Override
    public Long increaseAndGetInquiryId() {
        return inquiryMap.size() + 1L;
    }
}
