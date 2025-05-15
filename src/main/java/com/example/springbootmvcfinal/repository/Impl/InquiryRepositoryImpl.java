package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.Inquiry;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private static final Map<Long, Inquiry> inquiryMap = new HashMap<>();

    public InquiryRepositoryImpl() {
        Inquiry inquiry1 = new Inquiry(1L, "테스트 제목1", "테스트 내용1", LocalDateTime.now(), "test");
        Inquiry inquiry2 = new Inquiry(2L, "테스트 제목2", "테스트 내용2", LocalDateTime.now(), "test");
        inquiryMap.put(inquiry1.getId(), inquiry1);
        inquiryMap.put(inquiry2.getId(), inquiry2);
    }

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
}
