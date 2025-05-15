package com.example.springbootmvcfinal.domain.attachment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
    private Long id;
    private Long inquiryId;
    private String originalFileName;
    private String savedFileName;
    private String contentType;
    private long size;
    private String uploadPath;
}
