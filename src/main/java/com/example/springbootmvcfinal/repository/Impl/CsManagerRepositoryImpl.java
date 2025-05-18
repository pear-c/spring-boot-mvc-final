package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.repository.CsManagerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CsManagerRepositoryImpl implements CsManagerRepository {

    private static final Map<String, CsManager> managerMap = new HashMap<>();

    @Override
    public CsManager findById(String id) {
        return managerMap.get(id);
    }

    @Override
    public void save(CsManager csManager) {
        managerMap.put(csManager.getId(), csManager);
    }
}
