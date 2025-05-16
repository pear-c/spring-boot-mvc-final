package com.example.springbootmvcfinal.domain.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Answer {
    private Long id;                    // 답변 번호
    private Long inquiryId;             // 문의 글 번호
    private String content;             // 답변 내용
    private String managerId;           // 답변한 담당자 ID
    private LocalDateTime createdAt;    // 답변 일시
}
