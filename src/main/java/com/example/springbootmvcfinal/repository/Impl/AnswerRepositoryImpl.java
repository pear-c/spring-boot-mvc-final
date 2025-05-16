package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private static final Map<Long, Answer> answerMap = new HashMap<>();

    public AnswerRepositoryImpl() {
        Answer answer = new Answer(1L, 2L, "답변 드립니다~", "admin", LocalDateTime.now());
        answerMap.put(answer.getId(), answer);
    }

    @Override
    public void save(Answer answer) {
        answerMap.put(answer.getId(), answer);
    }
}
