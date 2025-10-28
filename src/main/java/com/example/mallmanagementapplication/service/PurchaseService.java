package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> getPurchaseById(String id) {
        return purchaseRepository.findById(id);
    }

    public void deletePurchase(String id) {
        purchaseRepository.delete(id);
    }
}
