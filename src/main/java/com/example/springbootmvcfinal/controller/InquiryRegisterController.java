package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.attachment.Attachment;
import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryRegisterRequest;
import com.example.springbootmvcfinal.exception.ValidationFailedException;
import com.example.springbootmvcfinal.repository.AttachmentRepository;
import com.example.springbootmvcfinal.repository.InquiryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/inquiry/register")
public class InquiryRegisterController {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    private final InquiryRepository inquiryRepository;
    private final AttachmentRepository attachmentRepository;

    @GetMapping
    public String inquiryRegisterForm() {
        return "inquiryRegister";
    }

    @PostMapping
    public String inquiryRegister(@Valid @ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                                  BindingResult bindingResult,
                                  HttpSession session) throws IOException {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Customer customer = (Customer) session.getAttribute("loginCustomer");

        Inquiry newInquiry = new Inquiry(
                inquiryRepository.increaseAndGetInquiryId(),
                inquiryRegisterRequest.getTitle(),
                inquiryRegisterRequest.getCategory(),
                inquiryRegisterRequest.getContent(),
                LocalDateTime.now(),
                customer.getId(),
                false
        );
        inquiryRepository.save(newInquiry);

        List<MultipartFile> files = inquiryRegisterRequest.getFiles();
        if(files != null) {
            for(MultipartFile file : files) {
                if(!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    String savedFileName = UPLOAD_DIR + originalFileName;
                    file.transferTo(Paths.get(savedFileName));

                    Attachment attachment = new Attachment(
                            attachmentRepository.increaseAndGetAttachmentId(),
                            newInquiry.getId(),
                            originalFileName,
                            savedFileName,
                            file.getContentType(),
                            file.getSize(),
                            "/images"
                    );
                    attachmentRepository.save(attachment);
                }
            }
        }

        return "redirect:/cs";
    }
}
