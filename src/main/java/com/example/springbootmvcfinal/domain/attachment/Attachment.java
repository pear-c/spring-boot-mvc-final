package com.example.springbootmvcfinal.domain.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Attachment {
    private Long id;                    // 첨부파일 고유번호
    private Long inquiryId;             // 문의 글 번호
    private String originalFileName;    // 원본 파일명
    private String savedFileName;       // 저장될 파일명
    private String contentType;         // 콘텐츠 타입
    private long size;                  // 용량
    private String uploadPath;          // 업로드 경로
}
