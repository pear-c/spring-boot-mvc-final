package com.example.springbootmvcfinal.controller;

import com.example.springbootmvcfinal.domain.customer.Customer;
import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.exception.CustomerNotFoundException;
import com.example.springbootmvcfinal.repository.CsManagerRepository;
import com.example.springbootmvcfinal.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerLoginControllerTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CsManagerRepository csManagerRepository;
    @Mock
    HttpSession session;

    @InjectMocks
    private CustomerLoginController customerLoginController;

    @Test
    void loginWithoutSession() {
        when(session.getAttribute("loginCustomer")).thenReturn(null);
        when(session.getAttribute("loginAdmin")).thenReturn(null);

        String view = customerLoginController.login(session);
        assertEquals("loginForm", view);
    }

    @Test
    void loginCustomerWithSession() {
        when(session.getAttribute("loginCustomer")).thenReturn(new Customer("test", "12345", "홍길동"));

        String view = customerLoginController.login(session);
        assertEquals("redirect:/cs", view);
    }

    @Test
    void loginAdminWithSession() {
        when(session.getAttribute("loginCustomer")).thenReturn(null);
        when(session.getAttribute("loginAdmin")).thenReturn(new CsManager("admin", "12345", "CS담당자", "고객관리팀"));

        String view = customerLoginController.login(session);
        assertEquals("redirect:/cs/admin", view);
    }

    @Test
    void setAttributeAdminSession() {
        CsManager manager = new CsManager("admin", "12345", "CS담당자", "고객관리팀");
        when(csManagerRepository.findById("admin")).thenReturn(manager);

        String view = customerLoginController.doLogin("admin", "12345", session);

        verify(session).setAttribute("loginAdmin", manager);
        assertEquals("redirect:/cs/admin", view);
    }

    @Test
    void setAttributeCustomerSession() {
        Customer customer = new Customer("test", "12345", "홍길동");
        when(customerRepository.matches("test", "12345")).thenReturn(true);
        when(customerRepository.findById("test")).thenReturn(customer);

        String view = customerLoginController.doLogin("test", "12345", session);

        verify(session).setAttribute("loginCustomer", customer);
        assertEquals("redirect:/cs", view);
    }

    @Test
    void isCorrectLoginIdAndPassword() {
        when(customerRepository.matches("test", "0000")).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerLoginController.doLogin("test", "0000", session);
        });
    }
}