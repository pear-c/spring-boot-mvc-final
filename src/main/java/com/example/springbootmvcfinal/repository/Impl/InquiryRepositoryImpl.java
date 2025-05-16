package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private static final Map<Long, Inquiry> inquiryMap = new HashMap<>();

    public InquiryRepositoryImpl() {
        Inquiry inquiry1 = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Inquiry inquiry2 = new Inquiry(2L, "칭찬합니다.", InquiryCategory.PRAISE, "상품 아주 마음에 들어요", LocalDateTime.now(), "test", true);
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
