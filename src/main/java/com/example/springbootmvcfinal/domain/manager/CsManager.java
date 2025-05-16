package com.example.springbootmvcfinal.domain.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CsManager {
    private String id;
    private String password;
    private String name;
    private String department;
}
