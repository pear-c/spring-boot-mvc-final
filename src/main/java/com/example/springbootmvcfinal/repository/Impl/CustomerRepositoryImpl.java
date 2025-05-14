package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.Customer;
import com.example.springbootmvcfinal.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final Map<String , Customer> customerMap = new HashMap<>();

    public CustomerRepositoryImpl() {
        Customer customer = new Customer("test", "12345", "홍길동");
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(String id) {
        return customerMap.get(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(findById(id))
                .map(customer -> customer.getPassword().equals(password))
                .orElse(false);
    }
}
