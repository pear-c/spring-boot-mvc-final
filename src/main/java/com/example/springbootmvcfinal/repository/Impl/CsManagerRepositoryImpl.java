package com.example.springbootmvcfinal.repository.Impl;

import com.example.springbootmvcfinal.domain.manager.CsManager;
import com.example.springbootmvcfinal.repository.CsManagerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CsManagerRepositoryImpl implements CsManagerRepository {

    private static final Map<String, CsManager> managerMap = new HashMap<>();

    public CsManagerRepositoryImpl() {
        CsManager manager = new CsManager("admin", "12345", "CS담당자", "고객관리팀");
        managerMap.put(manager.getId(), manager);
    }

    @Override
    public CsManager findById(String id) {
        return managerMap.get(id);
    }
}
