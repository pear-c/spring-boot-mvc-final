package com.example.springbootmvcfinal.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class Inquiry {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String customerId;
}
