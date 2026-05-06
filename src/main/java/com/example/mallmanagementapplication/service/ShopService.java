package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.ShopRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository repo;

    public ShopService(ShopRepository repo) {
        this.repo = repo;
    }

    /* ========== LIST ALL (used elsewhere) ========== */
    public List<Shop> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER BY NAME + SORT ========== */
    public List<Shop> getFilteredAndSortedByName(
            String name,
            Sort sort
    ) {
        if (name == null || name.isBlank()) {
            return repo.findAll(sort);
        }
        return repo.findByNameContainingIgnoreCase(name.trim(), sort);
    }

    /* ========== GET BY ID ========== */
    public Shop getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found: " + id));
    }

    /* ========== SAVE ========== */
    public Shop save(Shop shop) {
        return repo.save(shop);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Shop not found: " + id);
        }
        repo.deleteById(id);
    }
}