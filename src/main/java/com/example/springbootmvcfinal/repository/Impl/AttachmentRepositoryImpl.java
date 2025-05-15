package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.repository.AttachmentRepository;

import java.util.List;

public class AttachmentRepositoryImpl implements AttachmentRepository {
    @Override
    public void save(Attachment file) {

    }

    @Override
    public List<Attachment> findByInquiryId(Long id) {
        return List.of();
    }
}
