package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.repository.MallRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {

    private final MallRepository repo;

    public MallService(MallRepository repo) {
        this.repo = repo;
    }

    public List<Mall> getAll() {
        return repo.findAll();
    }

    public Mall getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mall not found: " + id));
    }

    public Mall save(Mall mall) {
        return repo.save(mall);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Mall not found: " + id);
        }
        repo.deleteById(id);
    }
}
