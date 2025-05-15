package com.example.springbootmvcfinal.domain;

public enum InquiryCategory {
    COMPLAINT("불만 접수"),
    SUGGESTION("제안"),
    REFUND("환불/교환"),
    PRAISE("칭찬해요"),
    ETC("기타 문의");

    private final String label;

    InquiryCategory(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
