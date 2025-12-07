package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.repository.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository repo;

    public PurchaseService(PurchaseRepository repo) {
        this.repo = repo;
    }

    public List<Purchase> getAll() {
        return repo.findAll();
    }

    public Purchase getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found: " + id));
    }

    public Purchase save(Purchase purchase) {
        if (purchase.getAmount() == null ||
                purchase.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Amount must be positive!");
        }
        return repo.save(purchase);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Purchase not found: " + id);
        }
        repo.deleteById(id);
    }
}
