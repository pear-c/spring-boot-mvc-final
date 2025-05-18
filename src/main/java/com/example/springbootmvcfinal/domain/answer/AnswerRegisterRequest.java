package com.example.springbootmvcfinal.domain.answer;

import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AnswerRegisterRequest {
    @Size(min = 1, max = 40000)
    String content;
}
