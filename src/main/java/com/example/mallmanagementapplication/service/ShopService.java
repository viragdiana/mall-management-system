package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.ShopRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository repo;

    public ShopService(ShopRepository repo) {
        this.repo = repo;
    }

    public List<Shop> getAll() {
        return repo.findAll();
    }

    public Shop getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found: " + id));
    }

    public Shop save(Shop shop) {

        // Business validation: unique shop name per floor
        if (repo.existsByNameAndFloorId(shop.getName(), shop.getFloor().getId())) {
            throw new IllegalStateException("A shop with this name already exists on this floor!");
        }

        return repo.save(shop);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new EntityNotFoundException("Shop not found: " + id);

        repo.deleteById(id);
    }
}
