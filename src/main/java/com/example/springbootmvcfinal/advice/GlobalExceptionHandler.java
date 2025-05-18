package com.example.springbootmvcfinal.advice;

import com.example.springbootmvcfinal.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public String handleCustomerNotFound(Exception ex, Model model) {
        log.error("", ex);
        model.addAttribute("exception", "id 또는 비밀번호가 일치하지 않습니다.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(Exception ex, Model model) {
        log.error("", ex);
        model.addAttribute("exception", ex);
        return "error";
    }
}
