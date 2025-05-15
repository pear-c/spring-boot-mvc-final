package com.example.springbootmvcfinal.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class Inquiry {
    private Long id;                    // 문의 번호
    private String title;               // 제목
    private InquiryCategory category;   // 분류
    private String content;             // 본문
    private LocalDateTime createdAt;    // 작성일시

    private String customerId;          // 작성자
    private boolean answered;           // 답변 여부
}
