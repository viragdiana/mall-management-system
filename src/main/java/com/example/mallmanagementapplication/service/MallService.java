package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.repository.MallRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {

    private final MallRepository repo;

    public MallService(MallRepository repo) {
        this.repo = repo;
    }

    /* ========== LIST ALL (used elsewhere) ========== */
    public List<Mall> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER BY NAME + SORT ========== */
    public List<Mall> getFilteredAndSortedByName(
            String name,
            Sort sort
    ) {
        if (name == null || name.isBlank()) {
            return repo.findAll(sort);
        }
        return repo.findByNameContainingIgnoreCase(name.trim(), sort);
    }

    /* ========== GET BY ID ========== */
    public Mall getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mall not found: " + id));
    }

    /* ========== SAVE ========== */
    public Mall save(Mall mall) {

        if (mall.getName() == null || mall.getName().isBlank()) {
            throw new IllegalStateException("Mall must have a name!");
        }
        if (mall.getCity() == null || mall.getCity().isBlank()) {
            throw new IllegalStateException("Mall must have a city!");
        }
        if (mall.getCountry() == null || mall.getCountry().isBlank()) {
            throw new IllegalStateException("Mall must have a country!");
        }

        return repo.save(mall);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Mall not found: " + id);
        }
        repo.deleteById(id);
    }
}