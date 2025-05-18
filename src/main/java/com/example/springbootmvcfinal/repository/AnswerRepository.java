package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.answer.Answer;

public interface AnswerRepository {
    void save(Answer answer);
    Answer findById(Long id);
    Answer findByInquiryId(Long inquiryId);
    Long increaseAndGetAnswerId();
}
