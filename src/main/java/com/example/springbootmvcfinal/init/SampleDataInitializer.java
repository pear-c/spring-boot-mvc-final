package com.example.springbootmvcfinal.init;

import com.example.springbootmvcfinal.domain.answer.Answer;
import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryCategory;
import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SampleDataInitializer {

    private final CustomerRepository customerRepository;
    private final CsManagerRepository csManagerRepository;
    private final InquiryRepository inquiryRepository;
    private final AttachmentRepository attachmentRepository;
    private final AnswerRepository answerRepository;

    @PostConstruct
    public void init() {
        Customer customer1 = new Customer("test", "12345", "홍길동");
        Customer customer2 = new Customer("test2", "12345", "김철수");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        CsManager manager = new CsManager("admin", "12345", "CS담당자", "고객관리팀");
        csManagerRepository.save(manager);

        Inquiry inquiry1 = new Inquiry(1L, "상품 교환 원해요.", InquiryCategory.REFUND, "상품 여기 하자 있는데 교환되나요?", LocalDateTime.now(), "test", false);
        Inquiry inquiry2 = new Inquiry(2L, "칭찬합니다.", InquiryCategory.PRAISE, "상품 아주 마음에 들어요", LocalDateTime.now(), "test", true);
        inquiryRepository.save(inquiry1);
        inquiryRepository.save(inquiry2);

        Attachment attachment = new Attachment(1L,1L, "tmp_image.png", "tmp_image.png", "image/png", 10000L, "/images");
        attachmentRepository.save(attachment);

        Answer answer = new Answer(1L, 2L, "답변 드립니다~", "admin", LocalDateTime.now());
        answerRepository.save(answer);
    }

}
