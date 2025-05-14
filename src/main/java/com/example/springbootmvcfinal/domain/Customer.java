package com.example.springbootmvcfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Customer {
    private String id;
    private String password;
    private String name;
}
