package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.repository.MallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {

    private final MallRepository mallRepo;

    public MallService(MallRepository mallRepo) {
        this.mallRepo = mallRepo;
    }

    public void addMall(Mall mall) {
        mallRepo.save(mall);
    }

    public Mall getMall(String id) {
        return mallRepo.findById(id);
    }

    public List<Mall> getAllMalls() {
        return mallRepo.findAll();
    }

    public void deleteMall(String id) {
        mallRepo.delete(id);
    }
}
