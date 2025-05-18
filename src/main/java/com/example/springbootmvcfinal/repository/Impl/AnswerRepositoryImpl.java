package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.repository.AnswerRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private static final Map<Long, Answer> answerMap = new HashMap<>();

    @Override
    public void save(Answer answer) {
        answerMap.put(answer.getId(), answer);
    }

    @Override
    public Answer findById(Long id) {
        return answerMap.get(id);
    }

    @Override
    public Answer findByInquiryId(Long inquiryId) {
        List<Answer> answerList = new ArrayList<>(answerMap.values());
        for (Answer answer : answerList) {
            if(answer.getInquiryId().equals(inquiryId)) {
                return answer;
            }
        }
        return null;
    }

    @Override
    public Long increaseAndGetAnswerId() {
        return answerMap.size() + 1L;
    }
}
