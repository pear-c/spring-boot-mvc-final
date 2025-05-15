package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.attachment.Attachment;

import java.util.List;

public interface AttachmentRepository {
    void save(Attachment file);
    List<Attachment> findByInquiryId(Long id);
}
