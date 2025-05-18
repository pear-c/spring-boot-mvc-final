package com.example.springbootmvcfinal.repository;

import com.example.springbootmvcfinal.domain.manager.CsManager;

public interface CsManagerRepository {
    CsManager findById(String id);
    void save(CsManager csManager);
}
