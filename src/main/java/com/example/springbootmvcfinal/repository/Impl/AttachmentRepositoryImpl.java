package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AttachmentRepositoryImpl implements AttachmentRepository {

    private static final Map<Long, Attachment> attachmentMap = new HashMap<>();

    public AttachmentRepositoryImpl() {
        Attachment attachment = new Attachment(
                1L,
                1L,
                "tmp_image.png",
                "tmp_image.png",
                "image/png",
                10000L,
                "/images"
        );
        attachmentMap.put(attachment.getId(), attachment);
    }

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
