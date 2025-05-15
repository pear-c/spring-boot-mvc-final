package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AttachmentRepositoryImpl implements AttachmentRepository {

    private static final Map<Long, Attachment> attachmentMap = new HashMap<>();

    @Override
    public void save(Attachment file) {
        attachmentMap.put(file.getId(), file);
    }

    @Override
    public List<Attachment> findByInquiryId(Long id) {
        return attachmentMap.values().stream()
                .filter(file -> file.getInquiryId().equals(id))
                .toList();
    }

    @Override
    public Long increaseAndGetAttachmentId() {
        return attachmentMap.size() + 1L;
    }
}
