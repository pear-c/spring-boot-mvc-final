package com.example.springbootmvcfinal.domain.inquiry;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
public class InquiryRegisterRequest {
    @NotEmpty
    @Size(min = 2, max = 200)
    String title;

    InquiryCategory category;

    @NotEmpty
    @Size(max = 40000)
    String content;

    MultipartFile file;
}
