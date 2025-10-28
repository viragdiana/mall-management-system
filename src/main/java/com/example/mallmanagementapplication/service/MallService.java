package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.repository.MallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MallService {
    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    public void addMall(Mall mall) {
        mallRepository.save(mall);
    }

    public List<Mall> getAllMalls() {
        return mallRepository.findAll();
    }

    public Optional<Mall> getMallById(String id) {
        return mallRepository.findById(id);
    }

    public void deleteMall(String id) {
        mallRepository.delete(id);
    }
}
