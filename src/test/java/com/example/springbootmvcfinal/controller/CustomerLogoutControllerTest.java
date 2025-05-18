package com.example.springbootmvcfinal.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerLogoutControllerTest {

    @Mock
    HttpSession session;

    @InjectMocks
    private CustomerLogoutController customerLogoutController;

    @Test
    void logout() {
        String view = customerLogoutController.logout(session);

        verify(session).invalidate();
        assertEquals("redirect:/cs/login", view);
    }
}