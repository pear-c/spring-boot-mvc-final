package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.inquiry.Inquiry;
import com.example.springbootmvcfinal.domain.inquiry.InquiryRegisterRequest;
import com.example.springbootmvcfinal.exception.ValidationFailedException;
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

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/inquiry/register")
public class InquiryRegisterController {
    private static final String UPLOAD_DIR = "/Users/pear_c/Downloads/";

    private final InquiryRepository inquiryRepository;

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
//
//        MultipartFile file = inquiryRegisterRequest.getFile();
//        file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));

        inquiryRepository.save(newInquiry);
        return "redirect:/cs";
    }
}
