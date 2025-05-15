package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.customer.Customer;

public interface CustomerRepository {
    Customer findById(String id);
    boolean matches(String id, String password);
}
